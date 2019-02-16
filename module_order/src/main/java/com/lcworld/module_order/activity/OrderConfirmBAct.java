package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityOrderconfirmBBinding;
import com.lcworld.module_order.viewmodel.OrderConfirmBViewModel;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;

/**
 * 确认订单（如意赚）
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Confirm2)
public class OrderConfirmBAct extends BaseActivityEnhance<OrderActivityOrderconfirmBBinding, OrderConfirmBViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_orderconfirm_b;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_Title();

        initObservable_QuantityChange();
    }

    private void initView_Title() {
        binding.qmuiTopbar.setTitle(R.string.order_create_title);
        binding.qmuiTopbar.addLeftImageButton(R.mipmap.arrow_left1, QMUIViewHelper.generateViewId());
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    private void initObservable_QuantityChange() {
        viewModel.valueQuantityOfGoods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                viewModel.uc.enableSub.set(viewModel.valueQuantityOfGoods.get() != 1);
                viewModel.uc.enableAdd.set(viewModel.valueQuantityOfGoods.get() != getResources().getInteger(R.integer.config_goodsquantity_maxlimit));
            }
        });
    }
}
