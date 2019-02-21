package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityPaymentResultBinding;
import com.lcworld.module_order.viewmodel.PaymentResultViewModel;

/**
 * 支付结果
 */
public class PaymentResultAct extends BaseActivityEnhance<OrderActivityPaymentResultBinding, PaymentResultViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_payment_result;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
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
