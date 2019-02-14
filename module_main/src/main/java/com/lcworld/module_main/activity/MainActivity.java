package com.lcworld.module_main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.module_main.BR;
import com.lcworld.module_main.R;
import com.lcworld.module_main.databinding.MainActivityMainBinding;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivityEnhance<MainActivityMainBinding, BaseViewModelEnhance> {
    private List<Fragment> mFragments;
    private Fragment mCurFrag;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.main_activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
        initFragment();
        initBottomTab();
    }

    private void initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_ENTRANCE).navigation();
        Fragment goodsFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_MEMBERAREA).navigation();
        Fragment mineFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_MINE).navigation();
        mFragments = new ArrayList<>();
        mFragments.add(homeFragment);
        mFragments.add(goodsFragment);
        mFragments.add(mineFragment);
        switchFragment(homeFragment);
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.naviBottom.material()
                .addItem(getDrawable(R.mipmap.main_navi_home_unselect), getDrawable(R.mipmap.main_navi_home_select)
                        , getString(R.string.main_navi_home), getResources().getColor(R.color.tx_colore))
                .addItem(getDrawable(R.mipmap.main_navi_member_select), getDrawable(R.mipmap.main_navi_member_unselect)
                        , getString(R.string.main_navi_classify), getResources().getColor(R.color.tx_colore))
                .addItem(getDrawable(R.mipmap.main_navi_mine_select), getResources().getDrawable(R.mipmap.main_navi_mine_unselect)
                        , getString(R.string.main_navi_mine), getResources().getColor(R.color.tx_colore))
                .setDefaultColor(getResources().getColor(R.color.navi_txunselect_color))
                .build();

        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                switchFragment(mFragments.get(index));
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        if (null == fragment) {
            return;
        }

        if (null == mCurFrag) {
            mCurFrag = fragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flayout, fragment).commit();
            return;
        }

        if (fragment != mCurFrag) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mCurFrag).show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mCurFrag)
                        .add(R.id.flayout, fragment).commit();
            }
            mCurFrag = fragment;
        }

    }
}
