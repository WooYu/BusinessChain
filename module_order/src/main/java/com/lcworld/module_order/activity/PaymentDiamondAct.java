package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityPaymentDiamondBinding;
import com.lcworld.module_order.viewmodel.PaymentDiamondViewModel;

/**
 * 商链钻值
 */
@Route(path = RouterActivityPath.Order.Pager_Payment_Diamond)
public class PaymentDiamondAct extends BaseActivityEnhance<OrderActivityPaymentDiamondBinding, PaymentDiamondViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_payment_diamond;
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

    private void initViewTitle(){
        binding.qmuiTopbar.setTitle(R.string.order_payment_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
