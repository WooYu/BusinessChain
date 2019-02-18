package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * 收货地址
 */
@Route(path = RouterActivityPath.Order.Pager_ReceiverAddress_List)
public class ReceiverAddressAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_receiveraddr);

        QMUITopBarLayout qmuiTopBar = findViewById(R.id.qmui_topbar);
        qmuiTopBar.setTitle(R.string.order_receiveraddr_title);
        qmuiTopBar.addLeftImageButton(R.mipmap.arrow_left1,QMUIViewHelper.generateViewId());
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
}
