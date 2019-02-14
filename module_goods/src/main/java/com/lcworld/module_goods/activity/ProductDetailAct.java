package com.lcworld.module_goods.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.*;
import android.widget.ImageView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_goods.BR;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.bean.DataGoodsGalleryInfo;
import com.lcworld.module_goods.databinding.GoodsActivityProductDetailsBinding;
import com.lcworld.module_goods.viewmodel.ProductDetailViewModel;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 商品详情
 */
@Route(path = RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
public class ProductDetailAct extends BaseActivityEnhance<GoodsActivityProductDetailsBinding, ProductDetailViewModel> {

    @Autowired(name = "goods_id")
    public int mGoodsID;


    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.goods_activity_product_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        ARouter.getInstance().inject(this);//添加在onCreate（）
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initView_Webview();
        initObservable_TopBanner();
        initObservable_Detail();

        viewModel.requestDetail(mGoodsID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.wvDetail.destroy();
    }

    private void initObservable_TopBanner() {
        viewModel.galleryInfoList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                updateView_TopBanner();
            }
        });
    }

    private void initObservable_Detail() {
        viewModel.productDetail.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.wvDetail.loadDataWithBaseURL(null, viewModel.productDetail.get(), "text/html", "utf-8", null);
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    private void initView_Webview() {
        binding.wvDetail.setWebChromeClient(webChromeClient);
        binding.wvDetail.setWebViewClient(webViewClient);

        WebSettings webSettings = binding.wvDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webSettings.setLoadWithOverviewMode(true);

    }

    private void updateView_TopBanner() {

        binding.viewBanner.setAdapter(new BGABanner.Adapter<ImageView, DataGoodsGalleryInfo>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable DataGoodsGalleryInfo model, int position) {
                GlideApp.with(ProductDetailAct.this)
                        .load(model.getThumbnail())
                        .into(itemView);
            }

        });

        binding.viewBanner.setAutoPlayAble(viewModel.galleryInfoList.size() > 1);
        binding.viewBanner.setData(viewModel.galleryInfoList, null);
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
//            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            KLog.i("拦截url:" + url);
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            KLog.i("网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setProgress(newProgress);
        }
    };


}
