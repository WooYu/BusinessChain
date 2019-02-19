package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityReceiveraddrBinding;
import com.lcworld.module_order.viewmodel.ReceiverAddressViewModel;

/**
 * 收货地址
 */
@Route(path = RouterActivityPath.Order.Pager_ReceiverAddress_List)
public class ReceiverAddressAct extends BaseActivityEnhance<OrderActivityReceiveraddrBinding, ReceiverAddressViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_receiveraddr;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initView_Title();
    }

    private void initView_Title() {
        binding.qmuiTopbar.setTitle(R.string.order_receiveraddr_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
