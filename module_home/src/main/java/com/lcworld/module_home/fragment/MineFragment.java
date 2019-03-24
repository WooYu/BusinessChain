package com.lcworld.module_home.fragment;

import android.animation.ArgbEvaluator;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.request.RequestOptions;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.base.BaseRefreshFragment;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.model.DataLogin;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.library_base.widget.scrollview.ScrollViewMine;
import com.lcworld.library_base_kotlin.Cache.LoginCache;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.databinding.HomeFragMineBinding;
import com.lcworld.module_home.viewmodel.MineViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.util.List;

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
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initView_GradienColor();
        initView_ScrollView();
        setLoginStatus();
        initLoginStatus();
        initBanner();
        startRefresh();
    }

    private void initBanner() {
        viewModel.focusPicturesObservableList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                updateView_Banner(sender);
            }
        });
    }

    private void updateView_Banner(List<DataFocusPictures> list) {
        binding.banner.setAdapter(new BGABanner.Adapter<ImageView, DataFocusPictures>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable DataFocusPictures model, int position) {
                GlideApp.with(getActivity())
                        .load(model.getPic_url())
                        .apply(new RequestOptions().placeholder(R.mipmap.home_holder).error(R.mipmap.home_holder).dontAnimate().centerCrop())
                        .into(itemView);
            }

        });

        binding.banner.setAutoPlayAble(viewModel.focusPicturesObservableList.size() > 1);
        binding.banner.setData(viewModel.focusPicturesObservableList, null);
    }


    private void initLoginStatus() {
        loginDisposable = RxBus.getDefault().toObservable(DataLogin.class)
                .subscribe(new Consumer<DataLogin>() {
                    @Override
                    public void accept(DataLogin data) throws Exception {
                        viewModel.isLogin.set(true);
                    }
                });
        RxSubscriptions.add(loginDisposable);
        viewModel.isLogin.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                setLoginStatus();

            }
        });
    }

    /**
     * 设置页面登录状态
     */
    private void setLoginStatus() {
        boolean isLogin = viewModel.isLogin.get();
        DataLogin loginData = LoginCache.INSTANCE.getLoginData();
        String userName = loginData == null ? "null" : loginData.getUsername();
        String loginName = isLogin ? userName : "未登录";
        viewModel.loginName.set(loginName);
        if (isLogin && loginData != null && loginData.getFace() != null && loginData.getFace().length() != 0) {
            viewModel.image.set(loginData.getFace());
        } else {
            binding.ivAvatar.setImageResource(R.mipmap.def_circle_a);
        }
    }

    @Override
    public void startRefresh() {
        viewModel.isLogin.set(LoginCache.INSTANCE.isLogin());
        viewModel.requestFocusPictures();
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
