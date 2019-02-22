package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityMyorderBinding;
import com.lcworld.module_order.fragment.OrderListFrag;
import com.lcworld.module_order.viewmodel.OrderReviewViewModel;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看订单
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Review)
public class OrderReviewAct extends BaseActivityEnhance<OrderActivityMyorderBinding, OrderReviewViewModel> {

    private List<OrderListFrag> mFragmentList;
    private OrderListFrag mCurFrag;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_myorder;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewTitle();
        initFragment();
        initTablayout();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_list_title);
        binding.qmuiTopbar.addRightImageButton(R.mipmap.order_title_calendar, QMUIViewHelper.generateViewId());
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        //ALL:所有订单,WAIT_PAY:待付款,WAIT_SHIP:待发货,WAIT_ROG:待收货," +"CANCELLED:已取消,COMPLETE:已完成,WAIT_COMMENT:待评论,REFUND:售后中"),
        String[] configOrderStatus = getResources().getStringArray(R.array.order_status);
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[0]));
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[2]));
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[5]));
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[4]));
        switchFragment(0);
    }

    private void initTablayout() {
        initIndicatorWidth();
        binding.naviTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switchFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initIndicatorWidth() {
        binding.naviTop.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout mTabStrip = (LinearLayout) binding.naviTop.getChildAt(0);
                try {
                    Field mTabs = TabLayout.class.getDeclaredField("mTabs");
                    mTabs.setAccessible(true);
                    ArrayList<TabLayout.Tab> tabs = (ArrayList<TabLayout.Tab>) mTabs.get(this);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        TabLayout.Tab tab = tabs.get(i);
                        Field mView = tab.getClass().getDeclaredField("mView");
                        mView.setAccessible(true);
                        Object tabView = mView.get(tab);
                        Field mTextView = getClassLoader().loadClass("android.support.design.widget.TabLayout$TabView").getDeclaredField("mTextView");
                        mTextView.setAccessible(true);
                        TextView textView = (TextView) mTextView.get(tabView);
                        float textWidth = textView.getPaint().measureText(textView.getText().toString());
                        View child = mTabStrip.getChildAt(i);
                        child.setPadding(0, 0, 0, 0);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) textWidth, LinearLayout.LayoutParams.MATCH_PARENT);
                        params.width = ConvertUtils.dp2px(getResources().getDimension(R.dimen.gap_size20));
                        params.height = ConvertUtils.dp2px(getResources().getDimension(R.dimen.gap_size2));
                        child.setLayoutParams(params);
                        child.invalidate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                try {
//                    //拿到tabLayout的mTabStrip属性
//                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
//
//                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
//                        View tabView = mTabStrip.getChildAt(i);
//
//                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
//                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
//                        mTextViewField.setAccessible(true);
//
//                        TextView mTextView = (TextView) mTextViewField.get(tabView);
//                        tabView.setPadding(0, 0, 0, 0);
//
//                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
//                        int width = 0;
//                        width = mTextView.getWidth();
//                        if (width == 0) {
//                            mTextView.measure(0, 0);
//                            width = mTextView.getMeasuredWidth();
//                        }
//                        int tabviewwidth = tabLayout.getMeasuredWidth() / tabLayout.getTabCount();
//
//                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
//                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
//                        params.width = ConvertUtils.dp2px(getResources().getDimension(R.dimen.gap_size20));
//                        params.height = ConvertUtils.dp2px(getResources().getDimension(R.dimen.gap_size2));
////                        params.leftMargin = (tabviewwidth - width) / 2;
////                        params.rightMargin = (tabviewwidth - width) / 2;
//                        tabView.setLayoutParams(params);
//                        tabView.invalidate();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void switchFragment(int position) {
        if (ObjectUtils.isEmpty(mFragmentList)) {
            return;
        }


        OrderListFrag fragment = mFragmentList.get(position);
        if (null == fragment) {
            return;
        }

        if (null == mCurFrag) {
            mCurFrag = fragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flayout_order, fragment).commit();
            return;
        }

        if (fragment != mCurFrag) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mCurFrag).show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mCurFrag)
                        .add(R.id.flayout_order, fragment).commit();
            }
            mCurFrag = fragment;
        }

    }

}
