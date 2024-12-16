package com.nolovr.shadow.core.plugin.app.lib.usecases.webview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nolovr.shadow.core.plugin.app.lib.R;
import com.nolovr.shadow.core.plugin.app.lib.gallery.cases.entity.UseCase;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 立体通方案
 */
public class WebView7Activity extends Activity {


    public static class Case extends UseCase {
        @Override
        public String getName() {
            return "WebView测试";
        }

        @Override
        public String getSummary() {
            return "测试WebView是否能正常工作";
        }

        @Override
        public Class getPageClass() {
            return WebView7Activity.class;
        }
    }

    private WebView webView;
    private FrameLayout fullVideo;
    private View customView = null;
    private ProgressBar progressBar;
    private static final String TAG = "llg-webview";
    // Mozilla/5.0 (Linux; Android 13; PFEM10 Build/SKQ1.220617.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/103.0.5060.129 Mobile Safari/537.36
    private String userAgent = "Mozilla/5.0 (Linux; Android 13; PFEM10 Build/SKQ1.220617.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/103.0.5060.129 Mobile Safari/537.36";//电脑UA,模拟谷歌浏览器。
//    private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";//电脑UA,模拟谷歌浏览器。
//    private String userAgent = "Mozilla/5.0 (Linux; iPad; PFEM10 Build/SKQ1.220617.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/103.0.5060.129 Mobile Safari/537.36";//ipad UA,模拟谷歌浏览器。

    //        private String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

    String h5GetVideoSrc = null;
    // 弹框
//    String h5GetVideoSrc = "var videos=document.getElementsByTagName('video');function pauseVideo(){var len=videos.length;for(var i=0;i<len;i++){videos[i].pause()}}var audios=document.getElementsByTagName('audio');function pauseAudio(){var len=audios.length;for(var i=0;i<len;i++){audios[i].pause()}}function jsGetVideoSrc(){alert('get android msg'); if(videos.length){for(var i=0;i<videos.length;i++){var v=videos[i];var _src=v.src;if((_src.startsWith('http')||_src.startsWith('https'))&&(_src.indexOf('.mp4')>-1||_src.indexOf('.m3u8?')>-1||url.indexOf('.avi')>-1||_src.indexOf('.mov')>-1||_src.indexOf('.mkv')>-1||_src.indexOf('.flv')>-1||_src.indexOf('.rmvb')>-1)){v.onplay=function(){var getVideoSrc=window.$Android.getVideoSrc;if(getVideoSrc){getVideoSrc(_src)}};break}}}};";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview7);
//        h5GetVideoSrc = String.valueOf(R.raw.videofind);
        InputStream inputStream = getResources().openRawResource(R.raw.videofind);
        h5GetVideoSrc = convertStreamToString(inputStream);
        checkPermission();

        webView = findViewById(R.id.webView);
        fullVideo = findViewById(R.id.full_video);
        progressBar = findViewById(R.id.progress);

        setWebView();


//        webView.loadUrl("https://www.iqiyi.com/");
//        webView.loadUrl("https://v.qq.com/");
//        webView.loadUrl("https://www.bilibili.com/");
        webView.loadUrl("https://www.youku.com/");
//        webView.loadUrl("https://m.bilibili.com/video/BV1xq4y1r7ax");
//        webView.loadUrl("http://192.168.1.78:3000/");
//        webView.loadUrl("https://8.147.104.161:3000/?info=true&roomId=broadcaster");
//        webView.loadUrl("https://www.jd.com/");
//        webView.loadUrl("https://v.qq.com/x/cover/mzc002007knmh3g/i0045u918s5.html");

        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient2());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WebChromeClient webChromeClient = webView.getWebChromeClient();
            if (webChromeClient != null) {
                Log.d(TAG, "onCreate: " + webChromeClient.toString());
            } else {
                Log.d(TAG, "onCreate: webChromeClient=null");
            }
        }

        //让js调用原生的方法
//        webView.addJavascriptInterface(myNative, "android");
//        webView.addJavascriptInterface(this, "getVideoSrc");
        webView.addJavascriptInterface(this, "$Android");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }

    private void setWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);     //支持JS
        webSettings.setDomStorageEnabled(true);       //启用dom内存，防止js加载失败
        webSettings.setAllowFileAccess(true);       //允许访问文件
        webSettings.setSupportZoom(true);       //支持缩放
        webSettings.setLoadWithOverviewMode(true);      //是否启动概述模式浏览界面，当页面宽度超过WebView显示宽度时，缩小页面适应WebView。默认false
        webSettings.setGeolocationEnabled(false);       //是否允许定位
        webSettings.setLoadsImagesAutomatically(true);
        //其中APP_NAME_UA是app自定义UA
        String userAgentString = webSettings.getUserAgentString();
//        webSettings.setUserAgentString(userAgentString + "nolo-llg");
//        webSettings.setUserAgentString(userAgent);
        Log.d(TAG, "setWebView:userAgentString= " + userAgentString);
    }


    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

        }
        return false;
    }


    public void printScreen(View view) {

        Log.d(TAG, "printScreen: 1");
        String imgPath = "/sdcard/test.png";
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap != null) {
            Log.d(TAG, "printScreen: 2");
            try {
                FileOutputStream out = new FileOutputStream(imgPath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                Log.d(TAG, "printScreen: 3");
            } catch (Exception e) {
                Log.d(TAG, "printScreen: e xxxxxxx");
                e.printStackTrace();
            }
        }
        Log.d(TAG, "printScreen: 4");

    }

    /**
     * 针对浏览器拦截处理
     */
    public class MyWebChromeClient2 extends WebChromeClient {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            Log.e(TAG, "WebChromeClient onCreateWindow: ");
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
            Log.e(TAG, "WebChromeClient onCloseWindow: ");
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
            Log.e(TAG, "WebChromeClient onReceivedTouchIconUrl: " + url.toString());
        }

        /**
         * 弹窗拦截
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.e(TAG, "WebChromeClient onJsAlert: ");
            return false;
//            return super.onJsAlert(view, url, message, result);
        }

        /**
         * 弹窗拦截
         */
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Log.e(TAG, "WebChromeClient onJsConfirm: ");
            return false;
//            return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 弹窗拦截
         */
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Log.e(TAG, "WebChromeClient onJsPrompt: ");
            return false;
//            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        /**
         * 加载进度拦截
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            Log.e(TAG, "WebChromeClient onProgressChanged: ");
            super.onProgressChanged(view, newProgress);
        }

        /**
         * 文件选择
         */
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            Log.e(TAG, "WebChromeClient onShowFileChooser: ");
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }

        /*** 视频播放相关的方法 **/
        @Override
        public View getVideoLoadingProgressView() {
            Log.e(TAG, "WebChromeClient getVideoLoadingProgressView: ");
            FrameLayout frameLayout = new FrameLayout(WebView7Activity.this);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }

        @Override
        public void onHideCustomView() {
            Log.e(TAG, "WebChromeClient onHideCustomView: ");
            printScreen(webView);

            if (customView == null) {
                return;
            }
            fullVideo.removeView(customView);
            fullVideo.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//清除全屏

        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            Log.e(TAG, "WebChromeClient onShowCustomView: ");
            printScreen(webView);
            customView = view;
            fullVideo.setVisibility(View.VISIBLE);
            fullVideo.addView(customView);
            fullVideo.bringToFront();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        }
    }


    private class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith("wtloginmqq://ptlogin/qlogin")) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(url);
                    sb.append("&schemacallback=");
                    WebView7Activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
                } catch (Exception unused) {
                    //   Main7Activity.this.b("没有安装QQ或者微信软件");
                }
                return true;
            } else if (!url.startsWith("http")) {
                Log.d(TAG, "shouldOverrideUrlLoading: title="+view.getTitle());
                Log.d(TAG, "shouldOverrideUrlLoading: url="+url);
                return true;
            } else {
                return false;
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "onPageStarted: ");
            super.onPageStarted(view, url, favicon);


        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (!url.startsWith("https://film.qq.com/weixin/login") || !url.startsWith("https://open.weixin.qq.com/")) {
                Log.d(TAG, "onPageFinished: exec js =》" + h5GetVideoSrc);
                view.loadUrl("javascript:" + h5GetVideoSrc);
                webView.evaluateJavascript(h5GetVideoSrc, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e(TAG, "onReceiveValue: value=" + value);
                    }
                });
            }


            progressBar.setVisibility(View.GONE);
            Log.d(TAG, "onPageFinished: ");
        }


        /**
         * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d(TAG, "onLoadResource:  getTitle= " + view.getTitle());
            Log.d(TAG, "onLoadResource:  getOriginalUrl= " + view.getOriginalUrl());
            super.onLoadResource(view, url);
            if (url.startsWith("http") && url.contains(".mp4")) {
                Log.e(TAG, "onLoadResource: xxxxx===>" + url);
            }
        }

        /**
         * (WebView发生改变时调用)
         * 可以参考http://www.it1352.com/191180.html的用法
         */
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            Log.d(TAG, "onScaleChanged: ");
            super.onScaleChanged(view, oldScale, newScale);
        }

        /**
         * 重写此方法才能够处理在浏览器中的按键事件。
         * 是否让主程序同步处理Key Event事件，如过滤菜单快捷键的Key Event事件。
         * 如果返回true，WebView不会处理Key Event，
         * 如果返回false，Key Event总是由WebView处理。默认：false
         */
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            Log.e(TAG, "xxxxxxxxxx  shouldOverrideKeyEvent: " + event.getKeyCode());
            return true;
        }

        /**
         * 是否重发POST请求数据，默认不重发。
         */
        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            Log.d(TAG, "onFormResubmission: ");
            super.onFormResubmission(view, dontResend, resend);
        }

        /**
         * 更新访问历史
         */
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            Log.d(TAG, "doUpdateVisitedHistory:  url=" + url + " ---isReload=" + isReload);
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        /**
         * 通知主程序输入事件不是由WebView调用。是否让主程序处理WebView未处理的Input Event。
         * 除了系统按键，WebView总是消耗掉输入事件或shouldOverrideKeyEvent返回true。
         * 该方法由event 分发异步调用。注意：如果事件为MotionEvent，则事件的生命周期只存在方法调用过程中，
         * 如果WebViewClient想要使用这个Event，则需要复制Event对象。
         */
        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            Log.d(TAG, "onUnhandledKeyEvent: ");
            super.onUnhandledKeyEvent(view, event);
        }


        /**
         * 通知主程序执行了自动登录请求。
         */
        @Override
        public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
            Log.d(TAG, "onReceivedLoginRequest: ");
            super.onReceivedLoginRequest(view, realm, account, args);
        }

        /**
         * 通知主程序：WebView接收HTTP认证请求，主程序可以使用HttpAuthHandler为请求设置WebView响应。默认取消请求。
         */
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            Log.d(TAG, "onReceivedHttpAuthRequest: ");
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        /**
         * 通知主程序处理SSL客户端认证请求。如果需要提供密钥，主程序负责显示UI界面。
         * 有三个响应方法：proceed(), cancel() 和 ignore()。
         * 如果调用proceed()和cancel()，webview将会记住response，
         * 对相同的host和port地址不再调用onReceivedClientCertRequest方法。
         * 如果调用ignore()方法，webview则不会记住response。该方法在UI线程中执行，
         * 在回调期间，连接被挂起。默认cancel()，即无客户端认证
         */
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
            Log.d(TAG, "onReceivedClientCertRequest: ");
            super.onReceivedClientCertRequest(view, request);
        }


    }


    private String convertStreamToString(InputStream inputstream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }


    @JavascriptInterface
    public void getVideoSrc(String who) {
        Log.e(TAG, "getVideoSrc: " + who);
        Toast.makeText(WebView7Activity.this, "js通过对象映射addJavascriptInterface的方式调用了原生的方法", Toast.LENGTH_SHORT).show();
    }


    @JavascriptInterface
    public void clickplaybtn(String str) {
        Log.e(TAG, "clickplaybtn:==================== ");
    }

    @JavascriptInterface
    public void logger(String str) {
        Log.e(TAG, "logger: =========================");
    }

    @JavascriptInterface
    public void playVideo(String str, String str2) {

        Log.e(TAG, "playVideo: =========================");

    }

    @JavascriptInterface
    public void playVideoOLD(String str, String str2) {
        Log.e(TAG, "playVideoOLD: ==================");
    }


}
