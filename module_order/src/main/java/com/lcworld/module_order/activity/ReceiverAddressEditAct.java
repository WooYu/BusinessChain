package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lcworld.module_order.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * 收货地址:编辑、新建
 */
public class ReceiverAddressEditAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_receiveraddr_edit);

        QMUITopBarLayout qmuiTopBar = findViewById(R.id.qmui_topbar);
        qmuiTopBar.setTitle(R.string.order_receiveraddr_edit_title);
        qmuiTopBar.addLeftImageButton(R.mipmap.arrow_left1,QMUIViewHelper.generateViewId());
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
}
