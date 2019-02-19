package com.lcworld.module_home.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ScreenUtils;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.library_base.widget.scrollview.ScrollViewMine;
import com.lcworld.module_home.R;
import com.lcworld.module_home.databinding.HomeFragMineBinding;
import com.lcworld.module_home.viewmodel.MineViewModel;
import me.goldze.mvvmhabit.BR;

/**
 * 我的
 */
@Route(path = RouterFragmentPath.Home.PAGER_MINE)
public class MineFragment extends BaseFragmentEnhance<HomeFragMineBinding, MineViewModel> {

    private float mColorOffsetThreshold;//状态栏变色阈值

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.home_frag_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        mColorOffsetThreshold = ScreenUtils.getScreenWidth() * 0.46f;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_GradienColor();
        initView_ScrollView();
    }

    private void initView_GradienColor() {
        binding.bgTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        binding.bgTitle.getBackground().setAlpha(0);
    }

    private void initView_ScrollView() {
        binding.scrollview.setOnScrollListener(new ScrollViewMine.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {


                if (0 == scrollY) {
                    binding.bgTitle.getBackground().setAlpha(0);
                } else {
                    binding.bgTitle.getBackground().setAlpha(255);
                    ArgbEvaluator evaluator = new ArgbEvaluator();
                    float fraction = 1;

                    if (0 < scrollY && scrollY <= mColorOffsetThreshold) {
                        fraction = scrollY / mColorOffsetThreshold;
                    }
                    int evaluate = (Integer) evaluator.evaluate(fraction, 0xFFFE8C00, 0xFFFE5200);
                    binding.bgTitle.setBackgroundColor(evaluate);
                }

            }
        });

    }


}
