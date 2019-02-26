package com.lcworld.module_order.fragment;

import android.databinding.Observable;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
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
            viewModel.requestOrderList(viewModel.valuePage.get());
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

        initViewRefreshLayout();
        initViewRecyclerView();

        initObservableEnableLoadMore();
        initObservableEnableRefresh();
        initObservableOrderList();
    }

    private void initViewRefreshLayout() {
        binding.swipeLayout.setColorSchemeColors(getResources().getColor(R.color.dominant_hue));
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.requestOrderList(1);
            }
        });
    }

    private void initViewRecyclerView() {
        mOrderAdapter = new OrderListAdapter(R.layout.order_item_myorder, viewModel.valueOrderList);
        mOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                viewModel.requestOrderList(viewModel.valuePage.get() + 1);
            }
        }, binding.rvOrder);
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

        mOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickedChildView(viewModelId, mOrderAdapter.getData().get(position).getSku_list().get(0).getGoods_id());
            }
        });
    }

    private void initObservableEnableLoadMore() {
        viewModel.valueEnableLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mOrderAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
            }
        });
    }

    private void initObservableEnableRefresh() {
        viewModel.valueEnableRefresh.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.swipeLayout.setRefreshing(viewModel.valueEnableRefresh.get());
            }
        });
    }

    private void initObservableOrderList() {
        viewModel.valueOrderList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mOrderAdapter.setNewData(viewModel.valueOrderList);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mOrderAdapter.setNewData(null);
            }
        });
    }

    //更新筛选条件
    public void updateFilter(String startTime, String endTime) {
        viewModel.valueStartTime.set(startTime);
        viewModel.valueEndTime.set(endTime);
        viewModel.requestOrderList(viewModel.valuePage.get());
    }

    //点击事件
    private void clickedChildView(int viewid, int goods_id) {
        if (viewid == R.id.btn_paid_pickup) {
            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Confirm2)
                    .withInt("sku_id", goods_id)
                    .navigation();
        } else if (viewid == R.id.btn_paid_buyagain || viewid == R.id.btn_settled_buyagain || viewid == R.id.btn_invalid_buyagain) {

        }

    }


}
