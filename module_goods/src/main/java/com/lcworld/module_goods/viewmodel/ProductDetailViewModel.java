package com.lcworld.module_goods.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.extension.EventPayResult;
import com.lcworld.library_base.http.*;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_goods.ApiServiceInterf;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.bean.DataGoodsDetailInfo;
import com.lcworld.module_goods.bean.DataGoodsGalleryInfo;
import com.lcworld.module_goods.bean.DataSKUVo;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.util.List;

public class ProductDetailViewModel extends BaseViewModelEnhance {

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
    }

    private Disposable disposablePayResult;

    public ObservableArrayList<DataGoodsGalleryInfo> galleryInfoList = new ObservableArrayList<>();
    public ObservableDouble dPrice = new ObservableDouble();
    public ObservableDouble oPrice = new ObservableDouble();
    public ObservableField<String> prodouctName = new ObservableField<>();
    public ObservableField<String> productDetail = new ObservableField<>();
    public ObservableField<String> productType = new ObservableField<>();
    public ObservableInt valueSkuId = new ObservableInt();

    public final BindingCommand clickOfBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        disposablePayResult = RxBus.getDefault().toObservable(EventPayResult.class)
                .subscribe(new Consumer<EventPayResult>() {
                    @Override
                    public void accept(EventPayResult eventPayResult) throws Exception {
                        finish();
                    }
                });
        RxSubscriptions.add(disposablePayResult);
    }

    @Override
    public void removeRxBus() {
        super.removeRxBus();
        RxSubscriptions.remove(disposablePayResult);
    }

    //请求商品详情
    public void requestDetail(int goods_id) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsDetail(goods_id)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataGoodsDetailInfo>>() {

                    @Override
                    public void onSuccess(RequestResult<DataGoodsDetailInfo> dataGoodsDetailInfoRequestResult) {
                        DataGoodsDetailInfo detailInfo = dataGoodsDetailInfoRequestResult.getData();
                        if (null == detailInfo) {
                            return;
                        }
                        galleryInfoList.clear();
                        galleryInfoList.addAll(detailInfo.getGallery_list());
                        dPrice.set(detailInfo.getPrice());
                        if (ObjectUtils.isNotEmpty(detailInfo.getSprice())) {
                            oPrice.set(detailInfo.getSprice());
                        }
                        prodouctName.set(detailInfo.getGoods_name());
                        productDetail.set(detailInfo.getIntro());
                        if (ObjectUtils.isEmpty(detailInfo.getGoods_type())) {
                            productType.set(getApplication().getResources().getStringArray(R.array.goods_type)[0]);
                        } else {
                            productType.set(detailInfo.getGoods_type());
                        }

                    }
                });

    }

    //请求商品SKU
    public void requestDetailSKU(int goods_id) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsDetailSKUS(goods_id)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataSKUVo>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataSKUVo>> listRequestResult) {
                        if (null == listRequestResult.getData()) {
                            return;
                        }
                        valueSkuId.set(listRequestResult.getData().get(0).getSku_id());
                    }
                });


    }

    //请求加入购物车
    public void requestAdd2ShoppingCart(int num) {
        int sku_id = valueSkuId.get();
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCarts(sku_id, num)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataSKUVo>>() {

                    @Override
                    public void onSuccess(RequestResult<DataSKUVo> dataSKUVoRequestResult) {
                        dialogControll_show(DialogControllTypeInterf.SUCCESS, getApplication().getString(R.string.goods_tip_add2shoppingcart));
                    }
                });
    }

    //请求立即购买
    public void requestBuyNow(int num) {
        int sku_id = valueSkuId.get();
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsBuy(sku_id, num)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp resultImp) {
                        String[] configGoodsType = getApplication().getResources().getStringArray(R.array.goods_type);
                        if (productType.get().equals(configGoodsType[3])) {
                            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Confirm2)
                                    .withInt("sku_id", valueSkuId.get())
                                    .navigation();
                        } else {
                            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Confirm1).navigation();
                        }

                    }
                });
    }

}
