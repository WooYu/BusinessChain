package com.lcworld.module_home.fragment;

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
import com.lcworld.module_home.R;
import com.lcworld.module_home.adapter.MessageListAdapter;
import com.lcworld.module_home.databinding.HomeFragMsglistBinding;
import com.lcworld.module_home.viewmodel.MsgListFragViewModel;
import me.goldze.mvvmhabit.BR;

/**
 * 消息列表
 */
public class HomeMsgListFrag extends BaseFragmentEnhance<HomeFragMsglistBinding, MsgListFragViewModel> {

    private MessageListAdapter mAdapter;

    public static HomeMsgListFrag getInstance(int type) {
        HomeMsgListFrag fragment = new HomeMsgListFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("msgtype", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.home_frag_msglist;
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
            int msgType = bundle.getInt("msgtype");
            viewModel.valueMsgType.set(msgType);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewRefreshLayout();
        initViewRecyclerView();
        initObservableEnableLoadMore();
        initObservableEnableRefresh();
        initObservableMessageList();

        viewModel.requestMsgList(1);
    }

    private void initViewRefreshLayout() {
        binding.swipeLayout.setColorSchemeColors(getResources().getColor(R.color.dominant_hue));
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.requestMsgList(1);
            }
        });
    }

    private void initViewRecyclerView() {
        mAdapter = new MessageListAdapter(R.layout.home_item_msg, null);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                viewModel.requestMsgList(viewModel.valuePage.get() + 1);
            }
        }, binding.rvList);
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                ARouter.getInstance().build(RouterActivityPath.Home.Pager_Message_Detail)
                        .withParcelable("msginfo", mAdapter.getData().get(position))
                        .navigation();
            }
        });
        binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvList.setAdapter(mAdapter);
    }

    private void initObservableEnableLoadMore() {
        viewModel.valueEnableLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
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

    private void initObservableMessageList() {
        viewModel.valueMsgList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mAdapter.setNewData(viewModel.valueMsgList);
                mAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mAdapter.setNewData(null);
                mAdapter.setEnableLoadMore(viewModel.valueEnableLoadMore.get());
            }
        });
    }
}
