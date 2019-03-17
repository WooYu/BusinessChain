package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.extension.utils.ConvertExUtils;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.adapter.OrderConfirmGoodsAdapter;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.databinding.OrderActivityOrderconfirmABinding;
import com.lcworld.module_order.viewmodel.OrderConfirmAViewModel;
import com.qmuiteam.qmui.util.QMUIViewHelper;

/**
 * 确认订单(普通、积分、商币)
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Confirm1)
public class OrderConfirmAAct extends BaseActivityEnhance<OrderActivityOrderconfirmABinding, OrderConfirmAViewModel> {
    private OrderConfirmGoodsAdapter mGoodsAdapter;
    private ConstraintLayout clayoutAddress;
    private Group groupDefAddr;
    private TextView tvDefAddrName;
    private TextView tvDefAddrPhone;
    private TextView tvDefAddrDesc;
    private Group groupNoAddr;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_orderconfirm_a;
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

        initObservableAddress();
        initObservableGoodsList();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_create_title);
        binding.qmuiTopbar.addLeftImageButton(R.mipmap.arrow_left1, QMUIViewHelper.generateViewId()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewRecyclerView() {

        mGoodsAdapter = new OrderConfirmGoodsAdapter(R.layout.order_item_goods, null);
        binding.rvGoods.setLayoutManager(new LinearLayoutManager(this));

        View headerView = getLayoutInflater().inflate(R.layout.order_layout_receiveraddr, (ViewGroup) binding.rvGoods.getParent(), false);
        clayoutAddress = headerView.findViewById(R.id.clayout_address);
        clayoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build(RouterActivityPath.Order.Pager_ReceiverAddress_List).navigation();
            }
        });
        groupNoAddr = headerView.findViewById(R.id.group_noAddr);
        groupDefAddr = headerView.findViewById(R.id.group_defAddr);
        groupDefAddr.setVisibility(View.GONE);
        groupNoAddr.setVisibility(View.VISIBLE);
        tvDefAddrName = headerView.findViewById(R.id.tv_address_name);
        tvDefAddrPhone = headerView.findViewById(R.id.tv_address_phone);
        tvDefAddrDesc = headerView.findViewById(R.id.tv_address_desc);

        View footerView = getLayoutInflater().inflate(R.layout.order_layout_paymethod, (ViewGroup) binding.rvGoods.getParent(), false);

        mGoodsAdapter.addHeaderView(headerView);
        mGoodsAdapter.addFooterView(footerView);
        binding.rvGoods.setAdapter(mGoodsAdapter);
    }


    private void initObservableAddress() {
        viewModel.observableAddress.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

                DataMemberAddress bean = viewModel.observableAddress.get();
                if (null == bean) {
                    groupDefAddr.setVisibility(View.GONE);
                    groupNoAddr.setVisibility(View.VISIBLE);
                } else {
                    groupDefAddr.setVisibility(View.VISIBLE);
                    groupNoAddr.setVisibility(View.GONE);

                    tvDefAddrName.setText(bean.getName());
                    tvDefAddrDesc.setText(bean.getAddr());
                    tvDefAddrPhone.setText(ConvertExUtils.convertPhone(bean.getMobile()));
                }
            }
        });
    }

    private void initObservableGoodsList() {
        viewModel.observableCartVoList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mGoodsAdapter.setNewData(viewModel.observableCartVoList);
            }
        });
    }
}
