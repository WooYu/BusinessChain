package com.lcworld.module_order.bean;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;

public class OrderListViewModel extends BaseViewModelEnhance {

    public OrderListViewModel(@NonNull Application application) {
        super(application);

    }

    public final ObservableField<String> valueOrderStatus = new ObservableField<>();
    public final ObservableInt valuePage = new ObservableInt(1);
    public final ObservableField<String> valueStartTime = new ObservableField();
    public final ObservableField<String> valueEndTime = new ObservableField();
    public final ObservableArrayList<DataOrderDTO> valueOrderList = new ObservableArrayList<>();
    public final ObservableBoolean valueEnableLoadMore = new ObservableBoolean();
    public final ObservableBoolean valueEnableRefresh = new ObservableBoolean();


    //请求订单列表
    public void requestOrderList(int requestPage) {
        if (ObjectUtils.isEmpty(valueOrderStatus.get())) {
            return;
        }

        if (requestPage == 1) {
            valueEnableRefresh.set(true);
            valueEnableLoadMore.set(false);
        }

        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeOrders("", "", valueOrderStatus.get(), requestPage
                        , getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)
                        , valueStartTime.get(), valueEndTime.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataOrderDTO>>() {
                    @Override
                    public void onSuccess(RequestResultPageImp<DataOrderDTO> dataOrderDTORequestResultPageImp) {

                        DataPage<DataOrderDTO> dataPage = dataOrderDTORequestResultPageImp.getData();
                        if (dataPage.getPage_no() == 1) {
                            valuePage.set(dataPage.getPage_no());
                            valueOrderList.clear();
                            if (dataPage.getData_total() != 0) {
                                valueOrderList.addAll(dataPage.getData());
                            }
                        } else {
                            if (dataPage.getData_total() != 0) {
                                valuePage.set(dataPage.getPage_no());
                                valueOrderList.addAll(dataPage.getData());
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
