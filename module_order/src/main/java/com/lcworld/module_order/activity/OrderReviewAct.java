package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.R;
import com.lcworld.module_order.fragment.OrderListFrag;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看订单
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Review)
public class OrderReviewAct extends AppCompatActivity {

    private List<OrderListFrag> mFragmentList;
    private OrderListFrag mCurFrag;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_myorder);
        tabLayout = findViewById(R.id.navi_top);
        QMUITopBarLayout qmuiTopBar = findViewById(R.id.qmui_topbar);
        qmuiTopBar.setTitle(R.string.order_list_title);
        qmuiTopBar.addRightImageButton(R.mipmap.order_title_calendar, QMUIViewHelper.generateViewId());
        qmuiTopBar.addLeftImageButton(R.mipmap.arrow_left1, QMUIViewHelper.generateViewId());
        QMUIStatusBarHelper.setStatusBarLightMode(this);

        initFragment();
        initTablayout();
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new OrderListFrag());
        mFragmentList.add(new OrderListFrag());
        mFragmentList.add(new OrderListFrag());
        mFragmentList.add(new OrderListFrag());

        switchFragment(0);
    }

    private void initTablayout() {
        initIndicatorWidth();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
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
