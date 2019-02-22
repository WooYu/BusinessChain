package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityOrderdetailsBinding;
import com.lcworld.module_order.viewmodel.OrderDetailViewModel;

/**
 * 订单详情
 */
public class OrderDetailAct extends BaseActivityEnhance<OrderActivityOrderdetailsBinding, OrderDetailViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_orderdetails;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            String order_sn = bundle.getString("order_sn");
            viewModel.valueOrderId.set(order_sn);
            viewModel.requestOrderDetail();
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewTitle();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_detail_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
