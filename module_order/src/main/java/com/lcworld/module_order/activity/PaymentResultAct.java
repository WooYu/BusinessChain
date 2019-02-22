package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityPaymentResultBinding;
import com.lcworld.module_order.viewmodel.PaymentResultViewModel;

/**
 * 支付结果
 */
@Route(path = RouterActivityPath.Order.Pager_Payment_Result)
public class PaymentResultAct extends BaseActivityEnhance<OrderActivityPaymentResultBinding, PaymentResultViewModel> {
    @Autowired(name = "pay_result")
    public boolean mPayResult;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_payment_result;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        ARouter.getInstance().inject(this);
        viewModel.valueOfPayResult.set(mPayResult);
        viewModel.valueOfPayResultTip.set(getString(
                mPayResult ? R.string.order_tip_payment_result_success : R.string.order_tip_payment_result_failed));
        binding.ivPicture.setImageResource(mPayResult?R.mipmap.order_payment_result_success:R.mipmap.order_payment_result_failed);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewTitle();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_payment_finish_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
