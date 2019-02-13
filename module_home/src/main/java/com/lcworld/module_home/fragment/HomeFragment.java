package com.lcworld.module_home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.module_home.R;
import com.lcworld.module_home.adapter.*;
import com.lcworld.module_home.databinding.HomeFragEntranceBinding;
import me.goldze.mvvmhabit.BR;

import java.util.LinkedList;
import java.util.List;

@Route(path = RouterFragmentPath.Home.PAGER_ENTRANCE)
public class HomeFragment extends BaseFragmentEnhance<HomeFragEntranceBinding, BaseViewModelEnhance> {

    private float mColorOffsetThreshold;//状态栏变色阈值
    private VirtualLayoutManager mVirtualLayoutManager;

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.home_frag_entrance;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        binding.bgSearchbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterActivityPath.Order.PAGER_TROLLEY).navigation();
//                startActivity(new Intent(getActivity(), OperatorApplyAct.class));
            }
        });

        initView_GradienColor();
        initView_StatusBar();
        initView_RecyclerView();
    }

    private void initView_GradienColor() {
        binding.bgTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        binding.viewPlaceholder.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        binding.bgTitle.getBackground().setAlpha(0);
        binding.viewPlaceholder.getBackground().setAlpha(0);
    }

    private void initView_StatusBar() {
        ConstraintLayout.LayoutParams barLayoutParams = (ConstraintLayout.LayoutParams) binding.viewPlaceholder.getLayoutParams();
        barLayoutParams.height = BarUtils.getStatusBarHeight();
    }

    private void initView_RecyclerView() {
        mVirtualLayoutManager = new VirtualLayoutManager(getActivity());
        binding.rvHome.setLayoutManager(mVirtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 10);
        binding.rvHome.setRecycledViewPool(recycledViewPool);
        DelegateAdapter delegateAdapter = new DelegateAdapter(mVirtualLayoutManager);
        binding.rvHome.setAdapter(delegateAdapter);
        binding.rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int alpha = 0;

                if (mColorOffsetThreshold < mVirtualLayoutManager.getOffsetToStart()) {
                    alpha = 255;
                } else {
                    alpha = (int) (mVirtualLayoutManager.getOffsetToStart() * 255.0 / mColorOffsetThreshold);
                }
                binding.viewPlaceholder.getBackground().setAlpha(alpha);
                binding.bgTitle.getBackground().setAlpha(alpha);
            }
        });

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        int margin = (int) getActivity().getResources().getDimension(R.dimen.dp_10);
        float topBgRatio = 2.7f;
        mColorOffsetThreshold = ScreenUtils.getScreenWidth() / topBgRatio;
        float topBannerOffset = ScreenUtils.getScreenWidth() / topBgRatio / 2;

        //顶部背景
        {
            SingleLayoutHelper topBgLayoutHelper = new SingleLayoutHelper();
            topBgLayoutHelper.setAspectRatio(topBgRatio);
            adapters.add(new HomeSubAdapter(getActivity(), topBgLayoutHelper, R.layout.home_item_sub_topbg, 1));
        }

        //轮播图
        {
            SingleLayoutHelper topBannerLayoutHelper = new SingleLayoutHelper();
            topBannerLayoutHelper.setAspectRatio(2.2f);
            topBannerLayoutHelper.setMargin(margin, (int) -topBannerOffset, margin, 0);
            adapters.add(new HomeSubTopBannerAdapter(getActivity(), topBannerLayoutHelper, 1));
        }

        //爆品抢购标题
        {
            LinearLayoutHelper hotSaleTitleLayoutHelper = new LinearLayoutHelper();
            hotSaleTitleLayoutHelper.setMargin(margin, 0, margin, 0);
            adapters.add(new HomeSubHotSaleTitleAdapter(getActivity(), hotSaleTitleLayoutHelper, new HomeSubHotSaleTitleAdapter.ItemClickListener() {
                @Override
                public void clickedMore() {
                    viewModel.dialogControll_show(DialogControllTypeInterf.TOAST, "查看更多");
                }
            }));
        }

        //爆品抢购列表
        {
            GridLayoutHelper hotSaleLayoutHelper = new GridLayoutHelper(2);
            hotSaleLayoutHelper.setAutoExpand(false);
            hotSaleLayoutHelper.setMargin(margin, 0, margin, 0);
            adapters.add(new HomeSubHotSaleAdapter(getActivity(), hotSaleLayoutHelper, new HomeSubHotSaleAdapter.ItemClickListener() {
                @Override
                public void clickedItem(int position) {
                    ToastUtils.showShort(position + " ");
                }
            }));
        }

        //分割线
        {
            LinearLayoutHelper gapLayoutHelper = new LinearLayoutHelper();
            adapters.add(new HomeSubAdapter(getActivity(), gapLayoutHelper, R.layout.home_item_sub_line, 1));
        }

        //拼团采集
        {
            LinearLayoutHelper spellBuyTitleLayoutHelper = new LinearLayoutHelper();
            spellBuyTitleLayoutHelper.setMargin(margin, 0, margin, 0);
            adapters.add(new HomeSubAdapter(getActivity(), spellBuyTitleLayoutHelper, R.layout.home_item_sub_spellpurchase_title, 1));
        }

        //拼团采集列表
        {
            LinearLayoutHelper spellBuyLayoutHelper = new LinearLayoutHelper();
            int size = 2;
            int marginBottom = (int) getResources().getDimension(R.dimen.gap_size13);
            spellBuyLayoutHelper.setMargin(margin, 0, margin, marginBottom);
            spellBuyLayoutHelper.setAspectRatio(3.5f);
            spellBuyLayoutHelper.setDividerHeight(marginBottom);
            adapters.add(new HomeSubSpellPurchaseAdapter(getActivity(), spellBuyLayoutHelper, size, new HomeSubSpellPurchaseAdapter.ItemClickListener() {
                @Override
                public void clickedItem(int position) {
                    ToastUtils.showShort(position + "");
                }
            }));
        }

        //分割线
        {
            LinearLayoutHelper gapLayoutHelper = new LinearLayoutHelper();
            adapters.add(new HomeSubAdapter(getActivity(), gapLayoutHelper, R.layout.home_item_sub_line, 1));
        }

        //招商加盟标题
        {
            LinearLayoutHelper investTitleLayoutHelper = new LinearLayoutHelper();
            investTitleLayoutHelper.setMargin(margin, 0, margin, 0);
            adapters.add(new HomeSubAdapter(getActivity(), investTitleLayoutHelper, R.layout.home_item_sub_invest_title, 1));
        }

        //招商加盟
        {
            LinearLayoutHelper investLayoutHelper = new LinearLayoutHelper();
            int size = 2;
            int marginBottom = (int) getResources().getDimension(R.dimen.gap_size13);
            investLayoutHelper.setMargin(margin, 0, margin, marginBottom);
            investLayoutHelper.setAspectRatio(3.5f);
            investLayoutHelper.setDividerHeight(marginBottom);
            adapters.add(new HomeSubInvestAdapter(getActivity(), investLayoutHelper, size, new HomeSubInvestAdapter.ItemClickListener() {
                @Override
                public void clickedItem(int position) {
                    ToastUtils.showShort(position + "");
                }
            }));
        }

        delegateAdapter.setAdapters(adapters);

    }
}
