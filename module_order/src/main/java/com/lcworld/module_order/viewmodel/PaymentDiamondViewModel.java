package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.library_base.http.*;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.activity.PaymentResultAct;
import com.lcworld.module_order.bean.DataPaymentMethodVo;
import com.lcworld.module_order.bean.DataTradeVo;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class PaymentDiamondViewModel extends BaseViewModelEnhance {
    public PaymentDiamondViewModel(@NonNull Application application) {
        super(application);

        initObservableValue();

        requestTradeCreate();
        requestPayMethod();
    }


    public final ObservableArrayList<DataPaymentMethodVo> valuePayMethodList = new ObservableArrayList<>();
    public final ObservableField<String> valueTotalPrice = new ObservableField<>();
    public final ObservableField<String> valueTradeSN = new ObservableField<>();

    public final ObservableBoolean enablePayNow = new ObservableBoolean();

    public final BindingCommand clickOfPayNow = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestInitiativePay();
        }
    });

    private void initObservableValue() {
        valueTotalPrice.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                enableBtnPayNow();
            }
        });
    }

    private void enableBtnPayNow() {
        boolean legalPrice = ObjectUtils.isNotEmpty(valueTotalPrice.get());

        enablePayNow.set(legalPrice);
    }

    //请求创建交易
    private void requestTradeCreate() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCreate(getApplication().getResources().getStringArray(R.array.client_type)[0])
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataTradeVo>>() {

                    @Override
                    public void onSuccess(RequestResult<DataTradeVo> dataTradeVoRequestResult) {
                        valueTradeSN.set(dataTradeVoRequestResult.getData().getTrade_sn());
                        valueTotalPrice.set(ConvertExUtils.formatMoney(dataTradeVoRequestResult.getData().getPrice_detail().getTotal_price()));
                    }
                });
    }

    //请求获取支付方式
    private void requestPayMethod() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .orderPayMethod(getApplication().getResources().getStringArray(R.array.client_type)[3])
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataPaymentMethodVo>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataPaymentMethodVo>> listRequestResult) {
                        valuePayMethodList.addAll(listRequestResult.getData());
                    }
                });
    }

    //请求对一个交易发起支付
    private void requestInitiativePay() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .orderShangBiPayInitiate(
                        valueTradeSN.get()
                        , valuePayMethodList.get(0).getPlugin_id())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        startActivity(PaymentResultAct.class);
                    }

                });
    }

}
