package com.jayson.demo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jayson.demo.R;
import com.jayson.demo.permission.EasyPermission;
import com.jayson.demo.permission.GrantResult;
import com.jayson.demo.permission.NextAction;
import com.jayson.demo.permission.Permission;
import com.jayson.demo.permission.PermissionRequestListener;
import com.jayson.demo.permission.RequestPermissionRationalListener;
import com.jayson.demo.ui.web.util.DownloadUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.utils.ToastUtils;

public class WebViewActivity_Down extends AppCompatActivity {

    private WebView mWebView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mContext = this;
        mWebView = findViewById(R.id.web_view);
        //https://github.com/panyiho/EasyPermission
        EasyPermission.with(this)
                .addPermissions(Permission.WRITE_EXTERNAL_STORAGE)      //写权限
                .addPermissions(Permission.READ_EXTERNAL_STORAGE)          //读取权限
                .addRequestPermissionRationaleHandler(Permission.ACCESS_FINE_LOCATION, new RequestPermissionRationalListener() {
                    @Override
                    public void onRequestPermissionRational(String permission, boolean requestPermissionRationaleResult, final NextAction nextAction) {
                        //这里处理具体逻辑，如弹窗提示用户等,但是在处理完自定义逻辑后必须调用nextAction的next方法
                    }
                })
                .addRequestPermissionRationaleHandler(Permission.CALL_PHONE, new RequestPermissionRationalListener() {
                    @Override
                    public void onRequestPermissionRational(String permission, boolean requestPermissionRationaleResult, final NextAction nextAction) {
                        //这里处理具体逻辑，如弹窗提示用户等,但是在处理完自定义逻辑后必须调用nextAction的next方法
                    }
                })
                .request(new PermissionRequestListener() {
                    @Override
                    public void onGrant(Map<String, GrantResult> result) {
                        //权限申请返回
                        initWebView();
                    }

                    @Override
                    public void onCancel(String stopPermission) {
                        //在addRequestPermissionRationaleHandler的处理函数里面调用了NextAction.next(NextActionType.STOP,就会中断申请过程，直接回调到这里来
                    }
                });
    }

    private void initWebView() {
        WebSettings setting = mWebView.getSettings();
        //允许js
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setMediaPlaybackRequiresUserGesture(false);
        setting.setLoadsImagesAutomatically(true);
        //自适应屏幕
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setLoadWithOverviewMode(true);
        //允许访问文件
        setting.setAllowFileAccess(true);
        // Allow use of Local Storage
        setting.setDomStorageEnabled(true);

        // 设置缓存路径
        String cacheDirPath = getFilesDir().getAbsolutePath() + "cache/";
        setting.setAppCachePath(cacheDirPath);
        // 设置缓存大小
        setting.setAppCacheMaxSize(5 * 1024 * 1024);
        //开启Application Cache存储机制
        setting.setAppCacheEnabled(true);

        mWebView.loadUrl("file:///android_asset/index.html");
        //mWebView.loadUrl("https://www.baidu.com");
        mWebView.setWebViewClient(new WebViewClient() {
            /*
            网络连接错误时调用
            */
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            /*
            网络开始加载时调用
            */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            /*
            网络加载结束时调用
            */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    //过滤
                    if (url.startsWith("baidu")) {
                        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        //startActivity(intent);//防止开启三方软件
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.contains(".mp4")) {
                    String miniType = MimeTypeMap.getSingleton()
                            .getMimeTypeFromExtension(getFileExtensionFromUrl(url));
                    Log.d("miniType", miniType);
                    File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_MOVIES)
                            , "game.mp4");
                    if (file.exists()) {
                        Log.d("status == ", "文件已存在，加载缓存..." + file.getAbsolutePath());
                        HashMap<String, String> header = new HashMap<>();
                        header.put("Access-Control-Allow-Origin", "*");
                        header.put("Access-Control-Allow-Headers", "Content-Type");
                        WebResourceResponse resourceResponse = null;
                        try {
                            resourceResponse = new WebResourceResponse(
                                    miniType, "", 200, "ok", header, new FileInputStream(file));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        return resourceResponse;
                    } else {
                        new DownloadUtils(mContext, request.getUrl().toString(), "game.mp4");
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebView.addJavascriptInterface(new JavaScriptFunc() {
            @Override
            @JavascriptInterface
            public void testNative(String param) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(param);
                    }
                });
            }
        }, "nativeAndroid");
    }

    interface JavaScriptFunc {
        void testNative(String param);
    }

    @Override
    public void onBackPressed() {
        if ((mWebView != null) && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private String getFileExtensionFromUrl(String path) {
        String url = path;
        url = url.toLowerCase();
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }
            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }
            int filenamePos = url.lastIndexOf('/');
            String filename = "";
            if (0 <= filenamePos) {
                filename = url.substring(filenamePos + 1);
            } else {
                filename = url;
            }
            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty()) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }
        return "";
    }
}
