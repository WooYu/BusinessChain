package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.adapter.PayMethodAdapter;
import com.lcworld.module_order.bean.AliPayResult;
import com.lcworld.module_order.databinding.OrderActivityPaymentChooseBinding;
import com.lcworld.module_order.viewmodel.PaymentChooseViewModel;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.Map;

/**
 * 选择支付方式
 */
@Route(path = RouterActivityPath.Order.Pager_Payment_Choose)
public class PaymentChooseAct extends BaseActivityEnhance<OrderActivityPaymentChooseBinding, PaymentChooseViewModel> {

    private PayMethodAdapter mPayMethodAdapter;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_payment_choose;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewTitle();
        initViewRecyclerView();

        initObservableAlipayOrderInfo();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_payment_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewRecyclerView() {
        mPayMethodAdapter = new PayMethodAdapter(R.layout.order_item_paymethod, viewModel.valuePayMethodList);
        binding.rvPaymethod.setLayoutManager(new LinearLayoutManager(this));
        mPayMethodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                viewModel.valueSelectPayMethodPosition.set(position);
                mPayMethodAdapter.setmChoicePosition(position);
            }
        });
        binding.rvPaymethod.setAdapter(mPayMethodAdapter);
    }

    private void initObservableAlipayOrderInfo() {
        viewModel.valueAlipayOrderInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                alipay_sendRequest(viewModel.valueAlipayOrderInfo.get());
            }
        });
    }


    //支付宝发送请求参数
    private void alipay_sendRequest(final String orderinfo) {
        if (ObjectUtils.isEmpty(orderinfo)) {
            return;
        }

        io.reactivex.Observable.create(new ObservableOnSubscribe<Map<String, String>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, String>> e) throws Exception {
                PayTask alipay = new PayTask(PaymentChooseAct.this);
                Map<String, String> result = alipay.payV2(orderinfo, true);
                e.onNext(result);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, String>>() {
                    @Override
                    public void accept(Map<String, String> result) throws Exception {
                        alipay_processingPaymentResult(result);
                    }
                });
    }

    //处理支付结果
    private void alipay_processingPaymentResult(Map<String, String> result) {
        AliPayResult payResult = new AliPayResult(result);
        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            startActivity(PaymentResultAct.class);
        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        }

    }

    //发送请求到微信
//    private void sendRequestToWechat(PayParamOfWechatResponse.OrderPayBean orderPayBean) {
//        if (null == orderPayBean) {
//            return;
//        }
//        PayReq request = new PayReq();
//        request.appId = orderPayBean.getAppid();
//        request.partnerId = orderPayBean.getPartnerid();
//        request.prepayId = orderPayBean.getPrepayid();
//        request.packageValue = orderPayBean.getPackageX();
//        request.nonceStr = orderPayBean.getNoncestr();
//        request.timeStamp = orderPayBean.getTimestamp();
//        request.sign = orderPayBean.getSign();
//        msgApi.sendReq(request);
//    }

}
