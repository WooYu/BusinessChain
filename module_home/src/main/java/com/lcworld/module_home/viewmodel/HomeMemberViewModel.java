package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;

import java.util.List;

public class HomeMemberViewModel extends BaseViewModelEnhance {
    public HomeMemberViewModel(@NonNull Application application) {
        super(application);
    }

    //分页大小
    public final ObservableInt observablePage = new ObservableInt(1);
    public final ObservableBoolean observableEnableLoadMore = new ObservableBoolean(true);

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
    public void requestMemberGoods(boolean isRefresh) {

        //如果是刷新，那么请求数据；如果是上拉加载，那么判断是否能请求数据。
        if (!isRefresh && !observableEnableLoadMore.get()) {
            return;
        }

        final int newPage = isRefresh ? 1 : (observablePage.get() + 1);
        int pageSize = getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize);
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsSearchQueryGoods(newPage, pageSize)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataGoodsInfo>>() {

                    @Override
                    public void onSuccess(RequestResultPageImp<DataGoodsInfo> dataGoodsInfoRequestResultPageImp) {
                        //请求成功了就去设置页码
                        observablePage.set(newPage);
                        if (dataGoodsInfoRequestResultPageImp.getData().getData_total() < getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)) {
                            //不够一页
                            observableEnableLoadMore.set(false);
                        } else {
                            observableEnableLoadMore.set(true);
                        }

                        //没有数据
                        if (dataGoodsInfoRequestResultPageImp.getData().getData_total() == 0) {
                            return;
                        }

                        if (1 == newPage) {
                            uc.memberGoodsObserableList.clear();
                        }
                        uc.memberGoodsObserableList.addAll(dataGoodsInfoRequestResultPageImp.getData().getData());

                    }
                });

    }

}
