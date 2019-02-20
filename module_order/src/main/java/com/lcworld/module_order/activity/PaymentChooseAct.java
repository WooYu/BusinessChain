package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * 选择支付方式
 */
@Route(path = RouterActivityPath.Order.Pager_Payment_Choose)
public class PaymentChooseAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_payment_choose);

        QMUITopBarLayout qmuiTopBar = findViewById(R.id.qmui_topbar);
        qmuiTopBar.setTitle(R.string.order_payment_title);
        qmuiTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
}
