package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.utils.ConvertExUtils;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataProportionDTO;
import com.lcworld.module_order.databinding.OrderActivityOrderconfirmBBinding;
import com.lcworld.module_order.viewmodel.OrderConfirmBViewModel;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

/**
 * 确认订单（如意赚）
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Confirm2)
public class OrderConfirmBAct extends BaseActivityEnhance<OrderActivityOrderconfirmBBinding, OrderConfirmBViewModel> {

    @Autowired(name = "sku_id")
    public int mSkuId;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_orderconfirm_b;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        ARouter.getInstance().inject(this);
        viewModel.valueSkuId.set(mSkuId);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_Title();

        initObservable_QuantityChange();
        initObservable_SalesCycle();
    }

    private void initView_Title() {
        binding.qmuiTopbar.setTitle(R.string.order_create_title);
        binding.qmuiTopbar.addLeftImageButton(R.mipmap.arrow_left1, QMUIViewHelper.generateViewId()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    private void initObservable_SalesCycle() {
        viewModel.uc.showSalesCycle.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showDialogSelectSalesCycle();
            }
        });
    }

    private void showDialogSelectSalesCycle() {
        if (ObjectUtils.isEmpty(viewModel.valuesSaleDayList) || viewModel.valuesSaleDayList.size() == 0) {
            return;
        }

        QMUIBottomSheet.BottomListSheetBuilder sheetBuilder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        for (int i = 0; i < viewModel.valuesSaleDayList.size(); i++) {
            DataProportionDTO bean = viewModel.valuesSaleDayList.get(i);
            sheetBuilder.addItem(String.format(getResources().getString(R.string.order_format_salescycle), bean.getDay()));
        }
        sheetBuilder
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        viewModel.valueDaysSales.set(tag);
                        viewModel.valueProfitRatio.set(
                                ConvertExUtils.convertProfitRatio(viewModel.valuesSaleDayList.get(position).getProportion()));
                        viewModel.valueSalesDayPosition.set(position);
                        viewModel.requestCalculateProfit();
                    }
                })
                .build()
                .show();
    }
}
