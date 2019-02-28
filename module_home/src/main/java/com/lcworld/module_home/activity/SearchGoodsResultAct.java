package com.lcworld.module_home.activity;

import android.databinding.Observable;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_home.BR;
import com.lcworld.module_home.R;
import com.lcworld.module_home.adapter.SearchGoodsAdapter;
import com.lcworld.module_home.databinding.HomeActivitySearchGoodsresultBinding;
import com.lcworld.module_home.viewmodel.SearchGoodsResultViewModel;

/**
 * 搜索商品结果
 */
public class SearchGoodsResultAct extends BaseActivityEnhance<HomeActivitySearchGoodsresultBinding, SearchGoodsResultViewModel> {

    private SearchGoodsAdapter mGoodsAdapter;
    private GridLayoutManager mGoodsLayoutManger;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_search_goodsresult;
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
            String key = bundle.getString("searchkey");
            viewModel.valueSearchKey.set(key);
            viewModel.requestGoodsList(1);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewRefreshLayout();
        initViewRecyclerView();

        initObservableEnableLoadMore();
        initObservableEnableRefresh();
        initObservableGoodsList();
        initObservableSwitchDisplayMode();
    }

    private void initViewRefreshLayout() {
        binding.swipeLayout.setColorSchemeColors(getResources().getColor(R.color.dominant_hue));
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.requestGoodsList(1);
            }
        });
    }

    private void initViewRecyclerView() {
        mGoodsAdapter = new SearchGoodsAdapter(R.layout.home_item_goods_linear, viewModel.valueGoodsList);
        mGoodsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                viewModel.requestGoodsList(viewModel.valuePage.get() + 1);
            }
        }, binding.rvGoods);
        mGoodsAdapter.setEnableLoadMore(false);
        mGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
                        .withInt("goods_id", mGoodsAdapter.getData().get(position).getGoods_id())
                        .navigation();
            }
        });
        binding.rvGoods.setLayoutManager(mGoodsLayoutManger = new GridLayoutManager(this, 1));
        binding.rvGoods.setAdapter(mGoodsAdapter);
    }

    private void initObservableEnableLoadMore() {
        viewModel.valueEnableLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mGoodsAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
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

    private void initObservableGoodsList() {
        viewModel.valueGoodsList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mGoodsAdapter.setNewData(viewModel.valueGoodsList);
                mGoodsAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mGoodsAdapter.setNewData(null);
                mGoodsAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
            }
        });
    }

    private void initObservableSwitchDisplayMode() {
        viewModel.checkStatusDisplayMode.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mGoodsLayoutManger.setSpanCount(viewModel.checkStatusDisplayMode.get() ? 1 : 2);
                mGoodsAdapter.switchDisplayMode(viewModel.checkStatusDisplayMode.get() ? R.layout.home_item_goods_linear : R.layout.home_item_goods_grid);
            }
        });
    }
}
