package com.lcworld.library_base.base;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.lcworld.library_base.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * 只加载网页的activity
 */
public class BrowseActivity extends AppCompatActivity {

    public final static String PARAM_URL = "param_url";
    public final static String PARAM_TITLE = "param_title";

    private String mUrl;
    private String mTitleName;
    private WebView mWebView;
    private QMUITopBarLayout mTopBarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        QMUIStatusBarHelper.setStatusBarLightMode(this);

        initData();
        initView();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            return;
        }

        mUrl = bundle.getString(PARAM_URL);
        mTitleName = bundle.getString(PARAM_TITLE);

    }

    private void initView() {
        mTopBarLayout = findViewById(R.id.qmui_topbar);
        mTopBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
            }
        });
        if (null != mTitleName) {
            mTopBarLayout.setTitle(mTitleName);
        }

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        settings.setSupportZoom(true);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(false);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage
        mWebView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //默认是打开系统自带的浏览器，重写这个方法之后就不会打开系统的浏览器了
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//解决webview 加载https 出现没内容
            }
        });

        if (null != mUrl) {
            mWebView.loadUrl(mUrl);
        }

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
