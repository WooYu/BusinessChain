package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;

import java.util.List;

public class HomeMemberViewModel extends BaseViewModelEnhance {
    public HomeMemberViewModel(@NonNull Application application) {
        super(application);
    }

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //轮播图数据变化
        public ObservableArrayList<DataFocusPictures> focusPicturesObservableList = new ObservableArrayList<>();
        //会员商品数据变化
        public ObservableArrayList<DataGoodsInfo> memberGoodsObserableList = new ObservableArrayList<>();
    }

    //请求轮播图
    public void requestFocusPictures() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .focusPictures(getApplication().getResources().getStringArray(R.array.client_type)[0]
                        , getApplication().getResources().getStringArray(R.array.page_type)[1])
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataFocusPictures>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataFocusPictures>> listRequestResult) {
                        if (!uc.focusPicturesObservableList.isEmpty()) {
                            uc.focusPicturesObservableList.clear();
                        }
                        uc.focusPicturesObservableList.addAll(listRequestResult.getData());
                    }
                });

    }

    //请求会员商品
    public void requestMemberGoods() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tagGoods(getApplication().getResources().getStringArray(R.array.mark_type)[0]
                        , getApplication().getResources().getInteger(R.integer.config_freebuy_displayquants))
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataGoodsInfo>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataGoodsInfo>> listRequestResult) {
                        if (!uc.memberGoodsObserableList.isEmpty()) {
                            uc.memberGoodsObserableList.clear();
                        }
                        uc.memberGoodsObserableList.addAll(listRequestResult.getData());
                    }
                });

    }

}
