package com.lcworld.module_home.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.google.gson.Gson;
import com.lcworld.library_base.base.BaseRefreshFragment;
import com.lcworld.library_base.global.SPKeyGlobal;
import com.lcworld.library_base.model.DataLogin;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.library_base.widget.scrollview.ScrollViewMine;
import com.lcworld.module_home.R;
import com.lcworld.module_home.databinding.HomeFragMineBinding;
import com.lcworld.module_home.viewmodel.MineViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

/**
 * 我的
 */
@Route(path = RouterFragmentPath.Home.PAGER_MINE)
public class MineFragment extends BaseRefreshFragment<HomeFragMineBinding, MineViewModel> {
    private Disposable loginDisposable;
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
    public void onDestroyView() {
        super.onDestroyView();
        RxSubscriptions.remove(loginDisposable);
    }

    @Override
    public void initData() {
        super.initData();
        mColorOffsetThreshold = ScreenUtils.getScreenWidth() * 0.46f;
        loginDisposable = RxBus.getDefault().toObservable(DataLogin.class)
                .subscribe(new Consumer<DataLogin>() {
                    @Override
                    public void accept(DataLogin data) throws Exception {
                        viewModel.loginName.set(data.getNickname());
                        if (data.getFace() != null && data.getFace().length() != 0) {
                            viewModel.image.set(data.getFace());
                        } else {
                            binding.ivAvatar.setImageResource(R.mipmap.def_circle_a);
                        }
                    }
                });
        RxSubscriptions.add(loginDisposable);
        binding.bgTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin()) {
                    ARouter.getInstance().build(RouterActivityPath.Account.PAGER_LOGIN).navigation();
                }
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_GradienColor();
        initView_ScrollView();
    }

    @Override
    public void startRefresh() {
        String loginData = SPUtils.getInstance().getString(SPKeyGlobal.KEY_LOGIN_INFO);
        DataLogin dataLogin = new Gson().fromJson(loginData, DataLogin.class);
        if (dataLogin != null) {
            viewModel.loginName.set(dataLogin.getNickname());
            if (dataLogin.getFace() != null && dataLogin.getFace().length() != 0) {
                viewModel.image.set(dataLogin.getFace());
            } else {
                binding.ivAvatar.setImageResource(R.mipmap.def_circle_a);
            }
        }
    }

    private boolean isLogin() {
        String loginData = SPUtils.getInstance().getString(SPKeyGlobal.KEY_LOGIN_INFO);
        DataLogin dataLogin = new Gson().fromJson(loginData, DataLogin.class);
        return dataLogin != null && dataLogin.getNickname() != null && dataLogin.getNickname().length() != 0;
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
