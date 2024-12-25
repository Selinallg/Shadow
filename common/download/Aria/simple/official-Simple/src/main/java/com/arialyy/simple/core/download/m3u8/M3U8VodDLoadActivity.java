/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arialyy.simple.core.download.m3u8;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.arialyy.annotations.Download;
import com.arialyy.annotations.M3U8;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.AbsEntity;
import com.arialyy.aria.core.common.HttpOption;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.M3U8Entity;
import com.arialyy.aria.core.download.m3u8.M3U8VodOption;
import com.arialyy.aria.core.processor.IBandWidthUrlConverter;
import com.arialyy.aria.core.processor.IKeyUrlConverter;
import com.arialyy.aria.core.processor.ITsMergeHandler;
import com.arialyy.aria.core.processor.IVodTsUrlConverter;
import com.arialyy.aria.core.task.DownloadTask;
import com.arialyy.aria.util.ALog;
import com.arialyy.aria.util.CommonUtil;
import com.arialyy.frame.util.show.T;
import com.arialyy.simple.R;
import com.arialyy.simple.base.BaseActivity;
import com.arialyy.simple.common.ModifyPathDialog;
import com.arialyy.simple.common.ModifyUrlDialog;
import com.arialyy.simple.databinding.ActivityM3u8VodBinding;
import com.arialyy.simple.to.PeerIndex;
import com.arialyy.simple.widget.ProgressLayout;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class M3U8VodDLoadActivity extends BaseActivity<ActivityM3u8VodBinding> {

    private static final String TAG = "_M3U8VodDLoadActivity";

    private String mUrl;
    private String mFilePath;
    private M3U8VodModule mModule;
    private VideoPlayerFragment mVideoFragment;
    private long mTaskId;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setTitle(getString(R.string.m3u8_file));
        Aria.download(this).register();
        mModule = ViewModelProviders.of(this).get(M3U8VodModule.class);
        mModule.getHttpDownloadInfo(this).observe(this, new Observer<DownloadEntity>() {

            @Override
            public void onChanged(@Nullable DownloadEntity entity) {
                if (entity == null) {
                    return;
                }
                if (entity.getM3U8Entity() != null) {
                    getBinding().seekBar.setMax(entity.getM3U8Entity().getPeerNum());
                }
                mTaskId = entity.getId();
                mUrl = entity.getUrl();
                mFilePath = entity.getFilePath();
                getBinding().pl.setInfo(entity);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mVideoFragment = new VideoPlayerFragment(0, entity);
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.video_content, mVideoFragment);
                ft.commit();
                Log.d(TAG, "onChanged: mUrl=" + mUrl);
                Log.d(TAG, "onChanged: mFilePath=" + mFilePath);
            }
        });
        getBinding().seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Aria.download(this).load(mTaskId).m3u8VodOption().jumPeerIndex(seekBar.getProgress());
            }
        });
        getBinding().pl.setBtListener(new ProgressLayout.OnProgressLayoutBtListener() {
            @Override
            public void create(View v, AbsEntity entity) {
                startD();
            }

            @Override
            public void stop(View v, AbsEntity entity) {
                Aria.download(this).load(mTaskId).stop();
            }

            @Override
            public void resume(View v, AbsEntity entity) {
                Aria.download(this)
                        .load(mTaskId)
                        .m3u8VodOption(getM3U8Option())
                        .resume();
            }

            @Override
            public void cancel(View v, AbsEntity entity) {
                Aria.download(this).load(mTaskId).cancel(true);
                mTaskId = -1;
            }
        });
    }

    public void chooseUrl() {
        ModifyUrlDialog dialog =
                new ModifyUrlDialog(this, getString(R.string.modify_url_dialog_title), mUrl);
        dialog.show(getSupportFragmentManager(), "ModifyUrlDialog");
    }

    public void chooseFilePath() {
        ModifyPathDialog dialog =
                new ModifyPathDialog(this, getString(R.string.modify_file_path), mFilePath);
        dialog.show(getSupportFragmentManager(), "ModifyPathDialog");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void jumpIndex(PeerIndex index) {
        Aria.download(this).load(mTaskId).m3u8VodOption().jumPeerIndex(index.index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_task_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int speed = -1;
        String msg = "";
        int itemId = item.getItemId();
        if (itemId == R.id.help) {
            msg = "一些小知识点：\n"
                    + "1、你可以在注解中增加链接，用于指定被注解的方法只能被特定的下载任务回调，以防止progress乱跳\n"
                    + "2、当遇到网络慢的情况时，你可以先使用onPre()更新UI界面，待连接成功时，再在onTaskPre()获取完整的task数据，然后给UI界面设置正确的数据\n"
                    + "3、你可以在界面初始化时通过Aria.download(this).load(URL).getPercent()等方法快速获取相关任务的一些数据";
            showMsgDialog("tip", msg);
        } else if (itemId == R.id.speed_0) {
            speed = 0;
        } else if (itemId == R.id.speed_128) {
            speed = 128;
        } else if (itemId == R.id.speed_256) {
            speed = 256;
        } else if (itemId == R.id.speed_512) {
            speed = 512;
        } else if (itemId == R.id.speed_1m) {
            speed = 1024;
        }
        if (speed > -1) {
            msg = item.getTitle().toString();
            Aria.download(this).setMaxSpeed(speed);
            T.showShort(this, msg);
        }
        return true;
    }

    @M3U8.onPeerStart
    void onPeerStart(String m3u8Url, String peerPath, int peerIndex) {
        //ALog.d(TAG, "peer create, path: " + peerPath + ", index: " + peerIndex);
    }

    @M3U8.onPeerComplete
    void onPeerComplete(String m3u8Url, String peerPath, int peerIndex) {
        //ALog.d(TAG, "peer complete, path: " + peerPath + ", index: " + peerIndex);
        //mVideoFragment.addPlayer(peerIndex, peerPath);
    }

    @M3U8.onPeerFail
    void onPeerFail(String m3u8Url, String peerPath, int peerIndex) {
        //ALog.d(TAG, "peer fail, path: " + peerPath + ", index: " + peerIndex);
    }

    @Download.onWait
    void onWait(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            Log.d(TAG, "wait ==> " + task.getDownloadEntity().getFileName());
        }
    }

    @Download.onPre
    protected void onPre(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            ALog.d(TAG, "pre");
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            ALog.d(TAG, "isComplete = " + task.isComplete() + ", state = " + task.getState());
            getBinding().seekBar.setMax(task.getEntity().getM3U8Entity().getPeerNum());
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            ALog.d(TAG,
                    "m3u8 void running, p = " + task.getPercent() + ", speed  = " + task.getConvertSpeed());
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            ALog.d(TAG, "m3u8 vod resume");
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            ALog.d(TAG, "stop");
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            Log.d(TAG, "cancel");
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task, Exception e) {
        if (task != null && task.getKey().equals(mUrl)) {
            Toast.makeText(M3U8VodDLoadActivity.this, getString(R.string.download_fail),
                            Toast.LENGTH_SHORT)
                    .show();
            Log.d(TAG, "fail");
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        if (task.getKey().equals(mUrl)) {
            Toast.makeText(M3U8VodDLoadActivity.this, getString(R.string.download_success),
                    Toast.LENGTH_SHORT).show();
            ALog.d(TAG, "md5: " + CommonUtil.getFileMD5(new File(task.getFilePath())));
            getBinding().pl.setInfo(task.getEntity());
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_m3u8_vod;
    }

    private void startD() {
        mTaskId = Aria.download(M3U8VodDLoadActivity.this)
                .load(mUrl)
                .setFilePath(mFilePath)
                .ignoreFilePathOccupy()
                .m3u8VodOption(getM3U8Option())
                .create();
    }


    //
    private M3U8VodOption getM3U8Option() {
        M3U8VodOption option = new M3U8VodOption();
        //.generateIndexFile()
        //.merge(true)
        //.setVodTsUrlConvert(new VodTsUrlConverter());
        //.setMergeHandler(new TsMergeHandler());
        //option.setUseDefConvert(true);
        option.setUseDefConvert(false);
//        option.setBandWidth(200000);
        option.setKeyUrlConverter(new KeyUrlConverter());
        option.setBandWidthUrlConverter(new BandWidthUrlConverter());
        option.setVodTsUrlConvert(new VodTsUrlConverter());
        //
//        option.generateIndexFile();
        // 合并成 可播放文件
        option.merge(true);
        return option;
    }


    // http://qn.shytong.cn/b83137769ff6b555/11b0c9970f9a3fa0.mp4.m3u8
    private M3U8VodOption getM3U8Option2() {
        M3U8VodOption option = new M3U8VodOption();
        //option.setBandWidth(200000);
        //.generateIndexFile()
        //.merge(true)
        //.setVodTsUrlConvert(new VodTsUrlConverter());
        //.setMergeHandler(new TsMergeHandler());
        //option.setUseDefConvert(true);
        option.setUseDefConvert(false);
        //option.setKeyUrlConverter(new KeyUrlConverter());
//        option.setBandWidthUrlConverter(new BandWidthUrlConverter());
        option.setVodTsUrlConvert(new VodTsUrlConverter());
        //
//        option.generateIndexFile();
        // 合并成 可播放文件
        option.merge(true);
        return option;
    }

    @Override
    protected void dataCallback(int result, Object data) {
        super.dataCallback(result, data);
        if (result == ModifyUrlDialog.MODIFY_URL_DIALOG_RESULT) {
            mModule.uploadUrl(this, String.valueOf(data));
        } else if (result == ModifyPathDialog.MODIFY_PATH_RESULT) {
            mModule.updateFilePath(this, String.valueOf(data));
        }
    }

    static class VodTsUrlConverter implements IVodTsUrlConverter {
        @Override
        public List<String> convert(String m3u8Url, List<String> tsUrls) {

            Log.d(TAG, "convert: m3u8Url=" + m3u8Url);

            for (String tsurl : tsUrls) {
                Log.d(TAG, "convert: tsurl=" + tsurl);
            }

            if (false) {
                Uri uri = Uri.parse(m3u8Url);
                //String parentUrl = "http://devimages.apple.com/iphone/samples/bipbop/gear1/";
                //String parentUrl = "http://youku.cdn7-okzy.com/20200123/16815_fbe419ed/1000k/hls/";
                //String parentUrl = "http://" + uri.getHost() + "/gear1/";
                //int index = m3u8Url.lastIndexOf("/");
                //String parentUrl = m3u8Url.substring(0, index + 1);
                //String parentUrl = "https://v1.szjal.cn/20190819/Ql6UD1od/";
                //String parentUrl = "http://" + uri.getHost() + "/";
                //List<String> newUrls = new ArrayList<>();
                //for (String url : tsUrls) {
                //  newUrls.add(parentUrl + url);
                //}

                //return newUrls;

                return tsUrls;
            }

            //m3u8Url:m3u8文件，包含ts文件
            //tsUrls:所有的ts文件
            /*某些ts文件带有全路径，如：http://xxxx.xxx.com/xxx.ts
             *有些没有只有相对路径，如：/20210927/3oCoCiM4/hls/xx.ts
             * 有些只有文件名，如：xxx.ts
             *因此需要区分，区分规则如下：
             * 如果包含有http或者https，一定是全路径，直接访问
             * 如果只有相对则是用域名+ts文件路径，如：(域名)http://xxxx.xxx.com/20210927/3oCoCiM4/hls/0.ts(路径)
             * 如果只有文件名，则是1 截取0到xxx.m3u8(不包含)的路径 2加上ts文件名，如：
             * http://xxxx.xxx.com/20210927/3oCoCiM4/index.m3u8
             * 去掉路径中xxx.m3u8的路径
             * http://xxxx.xxx.com/20210927/3oCoCiM4/
             * 加上ts文件名
             */

            Uri uri = Uri.parse(m3u8Url);
            String host = uri.getHost();
            String scheme = uri.getScheme();
            String authority = uri.getAuthority();

            Log.e(TAG, "convert: m3u8Url=" + m3u8Url);
            Log.e(TAG, "convert: host=" + host);
            Log.e(TAG, "authority: authority=" + authority);
            Log.e(TAG, "scheme: scheme=" + scheme);
            Log.e(TAG, "scheme: uri=" + uri.toString());
            List<String> newTslist = new ArrayList<>();
            String pattern = "[0-9a-zA-Z]+[.]ts";
            Pattern r = Pattern.compile(pattern);
            for (int i = 0; i < tsUrls.size(); i++) {
                String tspath = tsUrls.get(i);
                Matcher m = r.matcher(tspath);
                //全路径
                if (tspath.contains("http://") || tspath.contains("https://")) {
                    newTslist.add(tspath);
                }
                //只有文件名
                else if (m.find()) {
                    // 规则一
                    // http://hcjs2ra2rytd8v8np1q.exp.bcevod.com/mda-hegtjx8n5e8jt9zv/mda-hegtjx8n5e8jt9zv.m3u8
                    int e = m3u8Url.lastIndexOf("/") + 1;
                    String urlPath = m3u8Url.substring(0, e) + tspath;
                    newTslist.add(urlPath);
                    Log.d(TAG, "convert: urlPath==" + urlPath);
                    // 规则二
//                    int e = m3u8Url.lastIndexOf("/");
//                    String urlPath = m3u8Url.substring(0, e) + tspath;
//                    newTslist.add(urlPath);
//                    Log.d(TAG, "convert: urlPath==" + urlPath);
//                    // 规则三
                    // urlPath==http://qn.shytong.cn/b83137769ff6b555/11b0c9970f9a3fa0.mp4000000.ts
                    // http://qn.shytong.cn/b83137769ff6b555/11b0c9970f9a3fa0.mp4000004.ts
//                    int e = m3u8Url.lastIndexOf("/");
//                    String urlPath = scheme+ "://"+authority + tspath;
//                    Log.d(TAG, "convert: urlPath==" + urlPath);
                    // 规则四
                    // http://hcjs2ra2rytd8v8np1q.exp.bcevod.com/mda-hegtjx8n5e8jt9zv/mda-hegtjx8n5e8jt9zv.m3u8
//                    int e = m3u8Url.lastIndexOf("/");
//                    if (!tspath.startsWith("/")){
//                        tspath = "/"+tspath;
//                    }
//                    String urlPath = scheme + "://" + authority + tspath;
//                    newTslist.add(urlPath);
                }
                //相对路径
                else {

                    //String host= Host.gethost(m3u8Url);

                    Log.d(TAG, "convert: host=" + host);
                    newTslist.add(host + "/" + tspath);
                }
            }

            for (String url : newTslist) {
                Log.e(TAG, "convert: result--> url=" + url);
            }

            return newTslist;

        }
    }

    static class TsMergeHandler implements ITsMergeHandler {
        public boolean merge(@Nullable M3U8Entity m3U8Entity, List<String> tsPath) {
            ALog.d("TsMergeHandler", "合并TS....");
            return false;
        }
    }

    // #EXT-X-STREAM-INF
    static class BandWidthUrlConverter implements IBandWidthUrlConverter {


        @Override
        public String convert(String m3u8Url, String bandWidthUrl) {
            Log.d(TAG, "BandWidthUrlConverter convert: m3u8Url=" + m3u8Url);
            Log.d(TAG, "BandWidthUrlConverter convert: bandWidthUrl=" + bandWidthUrl);
            int index = m3u8Url.lastIndexOf("/");

            //m3u8Url:第一个m3u8，连接到下一个m3u8
            //bandWidthUrl:第二个m3u8，包含不同码率的ts视频
            /*第二个m3u8有些文件带有全路径，如：http://xxxx.xxx.com/xxx
             *有些没有，如：/20210927/3oCoCiM4/hls/index.m3u8
             *因此需要区分，区分规则如下：
             * 如果包含有http或者https，一定是全路径，直接访问
             * 如果没有则是用域名+第二个文件路径，如：(域名)http://xxxx.xxx.com/20210927/3oCoCiM4/hls/index.m3u8(路径)
             */
            if (bandWidthUrl.contains("http://") || bandWidthUrl.contains("https://")) {
                Log.d(TAG, "BandWidthUrlConverter convert: ---->" + bandWidthUrl);
                //return bandWidthUrl;
            } else {
                //获取域名
                try {
                    URL url = new URL(m3u8Url);
                    String host = url.getProtocol() + "://" + url.getHost() + ":" + url.getDefaultPort();
                    //return host+"/"+bandWidthUrl;
                    Log.d(TAG, "BandWidthUrlConverter convert: ====>" + host + "/" + bandWidthUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e(TAG, "BandWidthUrlConverter convert: xxxxxxxxxx" + e.getMessage());
                }
            }
            String result = m3u8Url.substring(0, index + 1) + bandWidthUrl;
            Log.e(TAG, "convert: sucess result=" + result);
            return result;
        }
    }

    static class KeyUrlConverter implements IKeyUrlConverter {

        @Override
        public String convert(String m3u8Url, String tsListUrl, String keyUrl) {
            ALog.e(TAG, "convertUrl....");
            return null;
        }
    }
}