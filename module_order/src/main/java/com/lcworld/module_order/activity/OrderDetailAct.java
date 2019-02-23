package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataOrderDTO;
import com.lcworld.module_order.bean.DataSKUVo;
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
        initObservableDetail();
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

    private void initObservableDetail() {
        viewModel.valueDataOrderDetail.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateView();
            }
        });
    }

    private void updateView() {
        DataOrderDTO orderDTO = viewModel.valueDataOrderDetail.get();
        binding.tvOrdernum.setText(String.format(getString(R.string.order_format_ordernum), orderDTO.getSn()));
        binding.tvOrderstatus.setText(orderDTO.getOrder_status_text());
        DataSKUVo skuVo = orderDTO.getOrder_sku_list().get(0);
        GlideApp.with(this)
                .load(skuVo.getGoods_image())
                .into(binding.ivPhoto);
        binding.tvName.setText(skuVo.getName());
        binding.tvPrice.setText(String.format(getString(R.string.format_money)
                , ConvertExUtils.formatMoney(skuVo.getPurchase_price())));
        binding.tvQuantity.setText(String.format(getString(R.string.order_format_purchasequantity_int), skuVo.getPurchase_num()));
        binding.tvAmountValue.setText(String.format(getString(R.string.format_money)
                , ConvertExUtils.formatMoney(orderDTO.getOrder_price())));
        binding.tvDeliverymethod.setText(orderDTO.getShip_status_text());
        binding.tvCreatetime.setText(String.valueOf(orderDTO.getCreate_time()));
        binding.tvPaymethod.setText(orderDTO.getPayment_name());
        binding.tvAddressValue.setText(orderDTO.getShip_addr());

        String order_status = orderDTO.getOrder_status();
        //ALL:所有订单,WAIT_PAY:待付款,WAIT_SHIP:待发货,WAIT_ROG:待收货," +"CANCELLED:已取消,COMPLETE:已完成,WAIT_COMMENT:待评论,REFUND:售后中"
        String[] statusArr = getResources().getStringArray(R.array.order_status);
        binding.groupPaid.setVisibility(View.GONE);
        binding.groupSettled.setVisibility(View.GONE);
        binding.groupInvalid.setVisibility(View.GONE);
        if (order_status.equals(statusArr[2]) || order_status.equals(statusArr[3])) {
            binding.groupPaid.setVisibility(View.VISIBLE);
        } else if (order_status.equals(statusArr[5])) {
            binding.groupSettled.setVisibility(View.VISIBLE);
        } else if (order_status.equals(statusArr[4])) {
            binding.groupInvalid.setVisibility(View.VISIBLE);
        }

    }
}
