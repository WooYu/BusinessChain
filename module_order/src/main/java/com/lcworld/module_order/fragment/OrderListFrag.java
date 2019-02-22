package com.lcworld.module_order.fragment;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.activity.OrderDetailAct;
import com.lcworld.module_order.adapter.OrderListAdapter;
import com.lcworld.module_order.bean.OrderListViewModel;
import com.lcworld.module_order.databinding.OrderFragMyorderBinding;

/**
 * 订单列表
 */
public class OrderListFrag extends BaseFragmentEnhance<OrderFragMyorderBinding, OrderListViewModel> {
    private OrderListAdapter mOrderAdapter;

    public static OrderListFrag instance(String status) {
        Bundle bundle = new Bundle();
        bundle.putString("order_status", status);
        OrderListFrag fragment = new OrderListFrag();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.order_frag_myorder;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (null != bundle) {
            String orderStatus = bundle.getString("order_status");
            viewModel.valueOrderStatus.set(orderStatus);
            viewModel.requestOrderList();
        }

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.valueOrderList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mOrderAdapter.setNewData(viewModel.valueOrderList);
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        mOrderAdapter = new OrderListAdapter(R.layout.order_item_myorder, viewModel.valueOrderList);
        mOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("order_sn", mOrderAdapter.getData().get(position).getSn());
                startActivity(OrderDetailAct.class, bundle);
            }
        });
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvOrder.setAdapter(mOrderAdapter);
    }
}
