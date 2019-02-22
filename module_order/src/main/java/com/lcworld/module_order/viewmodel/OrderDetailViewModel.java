package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.bean.DataOrderDTO;

public class OrderDetailViewModel extends BaseViewModelEnhance {
    public OrderDetailViewModel(@NonNull Application application) {
        super(application);

    }

    public final ObservableField<String> valueOrderId = new ObservableField<>();

    //请求提交订单
    public void requestOrderDetail() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeOrdersDetail(valueOrderId.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataOrderDTO>>() {

                    @Override
                    public void onSuccess(RequestResult<DataOrderDTO> dataOrderDTORequestResult) {

                    }
                });
    }
}
