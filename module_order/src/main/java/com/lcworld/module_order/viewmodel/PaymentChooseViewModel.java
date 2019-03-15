package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.base.BrowseActivity;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.library_base.http.*;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataPaymentMethodVo;
import com.lcworld.module_order.bean.DataTradeVo;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class PaymentChooseViewModel extends BaseViewModelEnhance {
    public PaymentChooseViewModel(@NonNull Application application) {
        super(application);

        initObservableValue();

        requestTradeCreate();
        requestPayMethod();
    }


    public final ObservableArrayList<DataPaymentMethodVo> valuePayMethodList = new ObservableArrayList<>();
    public final ObservableInt valueSelectPayMethodPosition = new ObservableInt(-1);
    public final ObservableField<String> valueTotalPrice = new ObservableField<>();
    public final ObservableField<String> valueTradeSN = new ObservableField<>();
    public final ObservableBoolean valueAgreeProtocol = new ObservableBoolean();
    public final ObservableField<String> valueAlipayOrderInfo = new ObservableField<>();
    public final ObservableField<String> valueWechatOrderInfo = new ObservableField<>();

    public final ObservableBoolean enablePayNow = new ObservableBoolean();

    public final BindingCommand clickOfProtocol = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("param_url", "https://lanhuapp.com/webapp/#/share?id=25385eb7-246a-4b30-8fd3-f55d1d1c6a8c&team_id=d46f37c5-d1cc-40ce-8532-0ec014ec21d9");
            bundle.putString("param_title", "银行");
            startActivity(BrowseActivity.class, bundle);
        }
    });

    public final BindingCommand clickOfPayNow = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String[] config_plugin_ids = getApplication().getResources().getStringArray(R.array.payment_plugin_id);
            if (valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id().equals(config_plugin_ids[3])) {
                requestInitiativePayBalance();
            } else {
                requestInitiativePay();
            }
        }
    });

    private void initObservableValue() {
        valueSelectPayMethodPosition.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                enableBtnPayNow();
            }
        });

        valueTotalPrice.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                enableBtnPayNow();
            }
        });


        valueAgreeProtocol.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                enableBtnPayNow();
            }
        });

    }

    private void enableBtnPayNow() {
        boolean legalPrice = ObjectUtils.isNotEmpty(valueTotalPrice.get());
        boolean legalPayMethod = -1 != valueSelectPayMethodPosition.get();
        boolean legalAgreeProtocol = valueAgreeProtocol.get();

        enablePayNow.set(legalPrice && legalPayMethod && legalAgreeProtocol);
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
                        valueTotalPrice.set(String.format(getApplication().getString(R.string.format_money)
                                , ConvertExUtils.formatMoney(dataTradeVoRequestResult.getData().getPrice_detail().getTotal_price())));
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

    //请求对一个交易发起支付(余额)
    private void requestInitiativePayBalance() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .balancePayTrade(valueTradeSN.get()
                        , valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Result)
                                .withBoolean("pay_result", true)
                                .navigation();
                        finish();
                    }

                });
    }

    //请求对一个交易发起支付（微信、支付宝）
    private void requestInitiativePay() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .orderPayInitiate(
                        valueTradeSN.get(), getApplication().getResources().getStringArray(R.array.trade_type)[0]
                        , valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id()
                        , getApplication().getResources().getStringArray(R.array.pay_mode)[0]
                        , getApplication().getResources().getStringArray(R.array.client_type)[3])
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        String[] config_plugin_ids = getApplication().getResources().getStringArray(R.array.payment_plugin_id);
                        if (valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id().equals(config_plugin_ids[1])) {
                            valueAlipayOrderInfo.set(requestResultImp.getData());
                        } else if (valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id().equals(config_plugin_ids[2])) {
                            valueWechatOrderInfo.set(requestResultImp.getData());
                        } else if (valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id().equals(config_plugin_ids[3])) {
                            requestQueryTradeResult();
                        }

                    }

                });
    }

    //请求查询交易结果
    public void requestQueryTradeResult() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .orderPayQueryResult(
                        valueTradeSN.get(), getApplication().getResources().getStringArray(R.array.trade_type)[0]
                        , valuePayMethodList.get(valueSelectPayMethodPosition.get()).getPlugin_id()
                        , getApplication().getResources().getStringArray(R.array.pay_mode)[0]
                        , getApplication().getResources().getStringArray(R.array.client_type)[3])
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Result)
                                .withBoolean("pay_result", true)
                                .navigation();
                        finish();
                    }

                });
    }

}
