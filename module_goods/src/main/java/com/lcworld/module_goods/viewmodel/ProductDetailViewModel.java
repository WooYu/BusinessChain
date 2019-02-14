package com.lcworld.module_goods.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_goods.ApiServiceInterf;
import com.lcworld.module_goods.bean.DataGoodsDetailInfo;
import com.lcworld.module_goods.bean.DataGoodsGalleryInfo;

public class ProductDetailViewModel extends BaseViewModelEnhance {


    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableArrayList<DataGoodsGalleryInfo> galleryInfoList = new ObservableArrayList<>();
    public ObservableDouble dPrice = new ObservableDouble();
    public ObservableDouble oPrice = new ObservableDouble();
    public ObservableField<String> prodouctName = new ObservableField<>();
    public ObservableField<String> productDetail = new ObservableField<>();

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
//                        oPrice.set(detailInfo.getSprice());
                        prodouctName.set(detailInfo.getGoods_name());
                        productDetail.set(detailInfo.getIntro());
                    }
                });

    }
}
