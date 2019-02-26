package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsInfo;

public class SearchGoodsResultViewModel extends BaseViewModelEnhance {

    public SearchGoodsResultViewModel(@NonNull Application application) {
        super(application);
        sortArr = application.getResources().getStringArray(R.array.goods_sort);
        valueSort.set(sortArr[0]);
    }

    public String[] sortArr;

    public final ObservableField<String> valueSearchKey = new ObservableField<>();
    public final ObservableInt valuePage = new ObservableInt(1);
    public final ObservableField<String> valueSort = new ObservableField();
    public final ObservableArrayList<DataGoodsInfo> valueGoodsList = new ObservableArrayList<>();
    public final ObservableBoolean valueEnableLoadMore = new ObservableBoolean();
    public final ObservableBoolean valueEnableRefresh = new ObservableBoolean();

    //请求商品列表
    public void requestGoodsList(int requestPageNo) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsSearch(requestPageNo, getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)
                        , valueSearchKey.get(), null, null, null, valueSort.get(), null, null, null)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataGoodsInfo>>() {

                    @Override
                    public void onSuccess(RequestResultPageImp<DataGoodsInfo> dataGoodsInfoRequestResultPageImp) {
                        DataPage<DataGoodsInfo> dataPage = dataGoodsInfoRequestResultPageImp.getData();
                        if (dataPage.getPage_no() == 1) {
                            valuePage.set(dataPage.getPage_no());
                            valueGoodsList.clear();
                            if (dataPage.getData_total() != 0) {
                                valueGoodsList.addAll(dataPage.getData());
                            }
                        } else {
                            if (dataPage.getData_total() != 0) {
                                valuePage.set(dataPage.getPage_no());
                                valueGoodsList.addAll(dataPage.getData());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        valueEnableLoadMore.set(true);
                        valueEnableRefresh.set(false);
                    }

                });

    }
}
