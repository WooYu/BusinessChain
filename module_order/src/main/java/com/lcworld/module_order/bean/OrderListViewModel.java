package com.lcworld.module_order.bean;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResultPageImp;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
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


    //请求订单列表
    public void requestOrderList() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeOrders("", "", valueOrderStatus.get(), valuePage.get()
                        , getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)
                        , valueStartTime.get(), valueEndTime.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataOrderDTO>>() {
                    @Override
                    public void onSuccess(RequestResultPageImp<DataOrderDTO> dataOrderDTORequestResultPageImp) {
                        valueOrderList.clear();
                        valueOrderList.addAll(dataOrderDTORequestResultPageImp.getData().getData());
                    }

                });
    }
}
