package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
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
    public final ObservableBoolean observableEnableLoadMore = new ObservableBoolean(true);//到底部触发加载更多

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

        int newPage = isRefresh ? 1 : (observablePage.get() + 1);
        int pageSize = getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize);

        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsSearchQueryGoods(newPage, pageSize)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataGoodsInfo>>() {

                    @Override
                    public void onSuccess(RequestResultPageImp<DataGoodsInfo> dataGoodsInfoRequestResultPageImp) {
                        DataPage<DataGoodsInfo> dataPage = dataGoodsInfoRequestResultPageImp.getData();
                        int curReturnDataSize = ObjectUtils.isEmpty(dataPage.getData()) ? 0 : dataPage.getData().size();
                        if (dataPage.getPage_no() == 1) {
                            observableEnableLoadMore.set(dataPage.getPage_size() == curReturnDataSize);
                            observablePage.set(dataPage.getPage_no());
                            uc.memberGoodsObserableList.clear();
                            if (curReturnDataSize != 0 ) {
                                uc.memberGoodsObserableList.addAll(dataPage.getData());
                            }

                        } else {
                            observableEnableLoadMore.set((dataPage.getPage_size() * (dataPage.getPage_no() - 1) + curReturnDataSize) < dataPage.getData_total());
                            if (curReturnDataSize != 0) {
                                observablePage.set(dataPage.getPage_no());
                                uc.memberGoodsObserableList.addAll(dataPage.getData());
                            }
                        }

                    }
                });

    }

}
