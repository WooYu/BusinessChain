package com.lcworld.module_order.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
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
import me.goldze.mvvmhabit.utils.KLog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 查看订单
 */
@Route(path = RouterActivityPath.Order.Pager_Order_Review)
public class OrderReviewAct extends BaseActivityEnhance<OrderActivityMyorderBinding, OrderReviewViewModel>
        implements DatePickerDialog.OnDateSetListener {

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        KLog.d("" + year + "-" + month + "-" + dayOfMonth);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        viewModel.valueSelectCalendar.set(calendar);

        //获取开始时间、结束时间
        String endtime = String.valueOf(calendar.getTimeInMillis() / 1000);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String starttime = String.valueOf(calendar.getTimeInMillis() / 1000);
        if (null != mCurFrag) {
            mCurFrag.updateFilter(starttime, endtime);
        }

    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_list_title);
        binding.qmuiTopbar.addRightImageButton(R.mipmap.order_title_calendar, QMUIViewHelper.generateViewId()).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickDatePickerDialog();
                    }
                }
        );
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
        String[] configOrderStatus = getResources().getStringArray(R.array.order_status_query);
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[0]));//ALL
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[1]));//pay
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[2]));//WAIT_COMMENT
        mFragmentList.add(OrderListFrag.instance(configOrderStatus[3]));//
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
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) binding.naviTop.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("textView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        int tabviewwidth = binding.naviTop.getMeasuredWidth() / binding.naviTop.getTabCount();

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.leftMargin = (tabviewwidth - width) / 2;
                        params.rightMargin = (tabviewwidth - width) / 2;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void clickDatePickerDialog() {
        Calendar calendar = viewModel.valueSelectCalendar.get();
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }
        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


}
