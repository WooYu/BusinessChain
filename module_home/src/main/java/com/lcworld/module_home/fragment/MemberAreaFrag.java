package com.lcworld.module_home.fragment;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.lcworld.library_base.base.BaseRefreshFragment;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.module_home.R;
import com.lcworld.module_home.adapter.HomeSubAdapter;
import com.lcworld.module_home.adapter.HomeSubTopBannerAdapter;
import com.lcworld.module_home.adapter.MemberAreaGoodsAdapter;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.lcworld.module_home.databinding.HomeFragMemberGoodsBinding;
import com.lcworld.module_home.viewmodel.HomeMemberViewModel;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.utils.KLog;

import java.util.LinkedList;
import java.util.List;

/**
 * 会员专区
 */
@Route(path = RouterFragmentPath.Home.PAGER_MEMBERAREA)
public class MemberAreaFrag extends BaseRefreshFragment<HomeFragMemberGoodsBinding, HomeMemberViewModel> {

    private VirtualLayoutManager mVirtualLayoutManager;
    private float mColorOffsetThreshold;//状态栏变色阈值
    private HomeSubTopBannerAdapter mTopBannerAdapter;
    private MemberAreaGoodsAdapter mMemberGoodsAdapter;

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.home_frag_member_goods;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_GradienColor();
        initView_StatusBar();
        initView_RecyclerView();

        initObservable_FoucsPicture();
        initObservable_MemberGoods();

        requestData();
    }

    @Override
    public void startRefresh() {
        viewModel.requestMemberGoods(true);
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
        binding.rvMemberarea.setLayoutManager(mVirtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 10);
        binding.rvMemberarea.setRecycledViewPool(recycledViewPool);
        DelegateAdapter delegateAdapter = new DelegateAdapter(mVirtualLayoutManager);
        binding.rvMemberarea.setAdapter(delegateAdapter);
        binding.rvMemberarea.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                if (!binding.rvMemberarea.canScrollVertically(1)) {
                    KLog.d("滑动到底部");
                    viewModel.requestMemberGoods(false);
                }
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
            adapters.add(mTopBannerAdapter = new HomeSubTopBannerAdapter(getActivity(), topBannerLayoutHelper, null));
        }

        //会员专区商品
        {
            GridLayoutHelper memberLayoutHelper = new GridLayoutHelper(2);
            memberLayoutHelper.setItemCount(2);
            memberLayoutHelper.setAutoExpand(false);
            int hgap = (int) getResources().getDimension(R.dimen.gap_size22);
            int vgap = (int) getResources().getDimension(R.dimen.gap_size14);
            memberLayoutHelper.setHGap(hgap);
            memberLayoutHelper.setVGap(vgap);
            int leftMargin = (int) getResources().getDimension(R.dimen.gap_size22);
            int topMargin = (int) getResources().getDimension(R.dimen.gap_size13);
            int rightMargin = (int) getResources().getDimension(R.dimen.gap_size17);

            memberLayoutHelper.setMargin(leftMargin, topMargin, rightMargin, 0);
            adapters.add(mMemberGoodsAdapter = new MemberAreaGoodsAdapter(getActivity(), memberLayoutHelper, new MemberAreaGoodsAdapter.ItemClickListener() {
                @Override
                public void clickedItem(int position) {
                    ARouter.getInstance().build(RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
                            .withInt("goods_id", mMemberGoodsAdapter.getmDatas().get(position).getGoods_id())
                            .navigation();
                }
            }));
        }

        delegateAdapter.setAdapters(adapters);
    }

    private void initObservable_FoucsPicture() {
        viewModel.uc.focusPicturesObservableList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                updateView_Banner(sender);
            }
        });
    }

    private void initObservable_MemberGoods() {
        viewModel.uc.memberGoodsObserableList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                updateView_MemberGoods(sender);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mMemberGoodsAdapter.setmDatas(null);
            }
        });
    }

    private void updateView_Banner(List<DataFocusPictures> list) {
        mTopBannerAdapter.setmDatas(list);
    }

    private void updateView_MemberGoods(List<DataGoodsInfo> list) {
        mMemberGoodsAdapter.setmDatas(list);
    }

    private void requestData() {
        viewModel.requestFocusPictures();
        viewModel.requestMemberGoods(true);
    }
}
