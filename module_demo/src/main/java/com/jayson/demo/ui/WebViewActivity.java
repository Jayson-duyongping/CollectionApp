package com.jayson.demo.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jayson.demo.R;

import me.goldze.mvvmhabit.utils.ToastUtils;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.web_view);
        initWebView();
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
}
