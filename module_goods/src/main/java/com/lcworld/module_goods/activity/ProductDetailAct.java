package com.lcworld.module_goods.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.module_goods.BR;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.bean.DataGoodsGalleryInfo;
import com.lcworld.module_goods.databinding.GoodsActivityProductDetailsBinding;
import com.lcworld.module_goods.fragment.GoodsPropertyFrag;
import com.lcworld.module_goods.viewmodel.ProductDetailViewModel;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 商品详情
 */
@Route(path = RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
public class ProductDetailAct extends BaseActivityEnhance<GoodsActivityProductDetailsBinding, ProductDetailViewModel> {

    @Autowired(name = "goods_id")
    public int mGoodsID;

    private TextView tvOperationServiceA;//tv_operation_service_a
    private TextView tvOperationTurn2ShoppingCart;//tv_operation_shoppingcart
    private Button btnOperationAdd2ShoppingCart;//btn_operation_addShoppingCart
    private Button btnOperationPayNow;//btn_operation_paynow
    private TextView tvOperationServiceB;//tv_operation_service_b
    private Button btnOperationConfirmBuy;//btn_operation_confirmbuy

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
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initView_Share();
        initView_Webview();
        initObservable_TopBanner();
        initObservable_DetailIntro();
        initObservable_GoodsType();
        initObservable_ServiceTel();

        viewModel.requestDetail(mGoodsID);
        viewModel.requestDetailSKU(mGoodsID);
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

    private void initObservable_DetailIntro() {
        viewModel.productDetail.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.wvDetail.loadDataWithBaseURL(null, viewModel.productDetail.get(), "text/html", "utf-8", null);
            }
        });
    }

    private void initObservable_GoodsType() {
        viewModel.productType.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateView_PriceUnit();
                updateView_BottomOperation();
            }
        });
    }

    private void initObservable_ServiceTel() {
        viewModel.valueServiceTel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                dialog_showServiceTel();
            }
        });
    }

    private void initView_Share() {
        binding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = (DialogFragment) ARouter.getInstance().build(RouterFragmentPath.Share.Pager_Entrance).navigation();
                fragment.show(getSupportFragmentManager(), RouterFragmentPath.Share.Pager_Entrance);
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    private void initView_Webview() {
        binding.wvDetail.setWebViewClient(webViewClient);

        WebSettings webSettings = binding.wvDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//把html中的内容放大webview等宽的一列中
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

    private void updateView_PriceUnit() {
        String[] configGoodsType = getResources().getStringArray(R.array.goods_type);
        if (viewModel.productType.get().equals(configGoodsType[2])) {
            binding.ivFlagPrice1.setImageResource(R.mipmap.goods_detail_diamond_a);
            binding.ivFlagPrice2.setImageResource(R.mipmap.goods_detail_diamond_b);
        } else {
            binding.ivFlagPrice1.setImageResource(R.mipmap.goods_detail_money_a);
            binding.ivFlagPrice2.setImageResource(R.mipmap.goods_detail_money_b);
        }
    }

    private void updateView_BottomOperation() {
        //商品类型:NORMAL普通\POINT积分\SHANGBI商币\PINTUAN拼团
        final String[] configGoodsType = getResources().getStringArray(R.array.goods_type);
        if ((viewModel.productType.get().equals(configGoodsType[0]) || viewModel.productType.get().equals(configGoodsType[1]))
                && null != binding.vStubBottomA.getViewStub()) {
            binding.vStubBottomA.getViewStub().inflate();
            tvOperationServiceA = findViewById(R.id.tv_operation_service_a);
            tvOperationTurn2ShoppingCart = findViewById(R.id.tv_operation_shoppingcart);
            btnOperationAdd2ShoppingCart = findViewById(R.id.btn_operation_addShoppingCart);
            btnOperationPayNow = findViewById(R.id.btn_operation_paynow);

            tvOperationServiceA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedServiceTel();
                }
            });
            tvOperationTurn2ShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(RouterActivityPath.Order.Pager_Trolley).navigation();
                }
            });
            btnOperationPayNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.valueIsBuyNow.set(true);
                    dialog_showProductChoose();
                }
            });
            btnOperationAdd2ShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.valueIsBuyNow.set(false);
                    dialog_showProductChoose();
                }
            });
        } else if ((viewModel.productType.get().equals(configGoodsType[2]) || viewModel.productType.get().equals(configGoodsType[3]))
                && null != binding.vStubBottomB.getViewStub()) {
            binding.vStubBottomB.getViewStub().inflate();
            tvOperationServiceB = findViewById(R.id.tv_operation_service_b);
            btnOperationConfirmBuy = findViewById(R.id.btn_operation_confirmbuy);
            tvOperationServiceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedServiceTel();
                }
            });
            btnOperationConfirmBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.valueIsBuyNow.set(true);
                    dialog_showProductChoose();
                }
            });
        }
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            KLog.i("拦截url:" + url);
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    private void clickedServiceTel() {
        if (ObjectUtils.isEmpty(viewModel.valueServiceTel.get())) {
            viewModel.requestServiceTel();
            return;
        }

        dialog_showServiceTel();
    }

    private void dialog_showServiceTel() {

        final NormalDialog dialog = new NormalDialog(this);
        dialog.content("确定拨打客户热线")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(15)
                .titleTextColor(getResources().getColor(R.color.tx_colorb))
                .contentTextColor(getResources().getColor(R.color.tx_colore))
                .content(viewModel.valueServiceTel.get())
                .contentTextSize(15)
                .btnTextColor(getResources().getColor(R.color.tx_colorb), getResources().getColor(R.color.tx_colorb))
                .btnTextSize(15, 15)
                .btnText("取消", "确定")
                .showAnim(new BounceTopEnter())//
                .dismissAnim(new SlideBottomExit())//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        requestPermission_CallPhone();
                        dialog.dismiss();
                    }
                });

    }

    private void dialog_showProductChoose() {
        if (viewModel.valueSKUVoList.isEmpty()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("skulist", viewModel.valueSKUVoList);
        GoodsPropertyFrag productFrag = GoodsPropertyFrag.getIntance(bundle);
        productFrag.show(getSupportFragmentManager(), GoodsPropertyFrag.class.getSimpleName());
    }

    private void requestPermission_CallPhone() {
        //请求打开相机权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            makeACall();
                        } else {
                            ToastUtils.showLong(R.string.permission_callphone_denied);
                        }
                    }
                });
    }

    private void makeACall() {
        //打电话
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + viewModel.valueServiceTel.get());
        intent.setData(data);
        startActivity(intent);
    }

}
