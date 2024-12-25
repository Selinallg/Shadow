package com.nolovr.core.download;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.AbsEntity;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.M3U8Entity;
import com.arialyy.aria.core.download.m3u8.M3U8VodOption;
import com.arialyy.aria.core.processor.IBandWidthUrlConverter;
import com.arialyy.aria.core.processor.IKeyUrlConverter;
import com.arialyy.aria.core.processor.ITsMergeHandler;
import com.arialyy.aria.core.processor.IVodTsUrlConverter;
import com.arialyy.aria.core.task.DownloadTask;
import com.arialyy.aria.util.ALog;
import com.arialyy.simple2.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadActivity extends PermissionActivity {

    private static final String TAG = "_DownloadActivity";
    private static final String DOWNLOAD_PATH = "/sdcard/gs3d/huimian2.m3u8.mp4";

    public static final String DOWNLOAD_URL =
//            "https://cdn.llscdn.com/yy/files/tkzpx40x-lls-LLS-5.7-785-20171108-111118.apk";
//            "http://videoconverter.vivo.com.cn/201706/655_1498479540118.mp4.main.m3u8";
//            "https://vod.pennonedu.com/bbca9230dce571ed80447035d0b20102/4ef2dccc67cd4106ac92bbe0617edd5e-d7029e35b1ece366ed2c53ac1a481939-sd.m3u8";
            "https://vod.pennonedu.com/b215b290c94b71edbffa6723a78f0102/90a1d51e9469430c9cd96139869be852-4f4145f0206446ffd968bc0ada1e1cfa-sd.m3u8";
    private long taskId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Aria.download(this).register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    @Override
    protected void onResulttPermissionsFail(String what) {
        Log.d(TAG, "onResulttPermissionsFail: ");
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
    }

    @Override
    public void initEnv() {
        Log.d(TAG, "initEnv: ");
    }


    public void getAllTaskList(View view) {
        Log.d(TAG, "getAllTaskList: ");
        // 所有任务列表
        List<AbsEntity> listAll = Aria.download(this).getTotalTaskList();
        // 普通任务列表
        List<DownloadEntity> list = Aria.download(this).getTaskList();

        if (listAll != null) {
            for (AbsEntity entity : listAll) {
                long rowID = entity.rowID;
                Log.d(TAG, "getAllTaskList: id=" + rowID + "  " + entity.toString());
            }
        }

        if (list != null) {
            for (DownloadEntity entity : list) {
                long rowID = entity.rowID;
                Log.d(TAG, "getAllTaskList2 : id=" + rowID + "  " + entity.toString());
            }
        }
    }


    public void resumeAllTask(View view) {
        Log.d(TAG, "resumeAllTask: ");
        Aria.download(this).resumeAllTask();
    }
    public void stopAllTask(View view) {
        Log.d(TAG, "stopAllTask: ");
        Aria.download(this).stopAllTask();
    }

    public void removeAllTask(View view) {
        Log.d(TAG, "removeAllTask: ");
        Aria.download(this).removeAllTask(true);
    }

    /**
     * 注意 m3u8 和普通下载的区别
     * @param view
     */
    public void goStart(View view) {
        //创建并启动下载
        taskId = Aria.download(this)
                .load(DOWNLOAD_URL)     //读取下载地址
//                .ignoreCheckPermissions()
//                .ignoreFilePathOccupy()
                .m3u8VodOption(getM3U8Option())
                .setFilePath(DOWNLOAD_PATH) //设置文件保存的完整路径
//                .add()
                .create();

        Log.d(TAG, "goStart: taskId=" + taskId);
    }

    public void goStop(View view) {
        Aria.download(this)
                .load(taskId)     //读取任务id
                .stop();       // 停止任务
        //.resume();
        Log.d(TAG, "goStop: ");

    }

    /**
     * 注意 m3u8 和普通下载的区别
     * @param view
     */
    public void goResume(View view) {
        Aria.download(this)
                .load(taskId)     //读取任务id
                .m3u8VodOption(getM3U8Option())
                .resume();       // 恢复任务
        //.resume();
        Log.d(TAG, "goResume: ");
    }

    public void goCancel(View view) {
        Aria.download(this)
                .load(taskId)     //读取任务id
                .cancel();       // 取消任务
        Log.d(TAG, "goCancel: ");

        Aria.download(this)
                .load(taskId)     //读取任务id
                .removeRecord();  // 取消任务
    }


    public void goTaskState(View view) {
        int taskState1 = Aria.download(this).load(taskId).getTaskState();
        int taskState2 = Aria.download(this).load(DOWNLOAD_URL).getEntity().getState();
        Log.d(TAG, "goTaskState: taskState1="+taskState1 + " taskState2="+taskState2);

    }

    public void goReset(View view) {

        // 重置状态 状态重置之后，任务将重新开始执行
        Aria.download(this).load(taskId).resetState();

//        // 修改文件保存信息
//        String newPath = "/sdard/gs3d/tmp.apk";
//        Aria.download(this).load(taskId).getEntity().setFilePath(newPath);


    }

    public void goDelRecord(View view) {

//        /**
//         * 删除任务记录
//         *
//         * @param type 需要删除的任务类型，1、表示单任务下载。2、表示任务组下载。3、单任务上传
//         * @param key 下载为保存路径、任务组为任务组名、上传为上传文件路径
//         *            type为1时，key为保存路径；type为2时，key为组合任务hash；type为3时，key为文件上传路径。
//         */
//        Aria.get(this).delRecord(type, key,true);
        // 删除下载记录
        Aria.download(this).load(taskId).removeRecord();
    }


    // 1
    @Download.onWait
    void onWait(DownloadTask task) {
        Log.d(TAG, "wait ==> " + task.getDownloadEntity().getFileName());
    }

    // 2
    @Download.onPre
    protected void onPre(DownloadTask task) {
        Log.d(TAG, "onPre");
    }


    // 3
    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        Log.d(TAG, "onStart");
    }

    // 4
    //在这里处理任务执行中的状态，如进度进度条的刷新
    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        Log.d(TAG, "running: " + task.getEntity().rowID);
        int p = task.getPercent();    //任务进度百分比
        String speed = task.getConvertSpeed();    //转换单位后的下载速度，单位转换需要在配置文件中打开
        long speed1 = task.getSpeed(); //原始byte长度速度
        Log.d(TAG, "running: p=" + p + " speed=" + speed);
    }

    // 5
    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        Log.d(TAG, "resume");
    }

    // 6
    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        Log.d(TAG, "stop");
    }

    // 7
    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        //在这里处理任务完成的状态
        Log.d(TAG, "taskCancel: " + task.getEntity().rowID);
    }

    // 8
    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        //在这里处理任务完成的状态
        try {
            Log.d(TAG, "taskFail: " + task.getEntity().rowID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 9
    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        if (task!=null){
            String filePath = task.getFilePath();
            String fileName = task.getEntity().getFileName();
            M3U8Entity m3U8Entity = task.getEntity().getM3U8Entity();
            if (m3U8Entity!=null){
                Log.d(TAG, "taskComplete: m3U8Entity="+m3U8Entity.toString());
            }else {
                Log.d(TAG, "taskComplete: m3U8Entity= null");
            }
            Log.d(TAG, "taskComplete: filePath="+filePath);
            Log.d(TAG, "taskComplete: fileName="+fileName);
        }
        Log.d(TAG, "taskComplete: " + task.getEntity().rowID);
    }


    //-------------------------内部接口--------------------
    private M3U8VodOption getM3U8Option() {
        M3U8VodOption option = new M3U8VodOption();
        //option.setBandWidth(200000);
        //.generateIndexFile()
        //.merge(true)
        //.setVodTsUrlConvert(new VodTsUrlConverter());
        //.setMergeHandler(new TsMergeHandler());
        option.setUseDefConvert(false);
//        option.generateIndexFile();
        //option.setKeyUrlConverter(new KeyUrlConverter());
        //option.setVodTsUrlConvert(new VodTsUrlConverter());
//        option.setBandWidthUrlConverter(new BandWidthUrlConverter());
        option.setVodTsUrlConvert(new VodTsUrlConverter());
        //option.setUseDefConvert(true);
        return option;
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