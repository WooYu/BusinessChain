package com.lcworld.module_order.ui;

import android.app.Activity;
import android.os.Bundle;
import com.lcworld.module_order.R;

/**
 * 一个MVVM模式的登陆界面
 * 作为登录验证模块的路由页
 */
public class EntranceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
    }
}

