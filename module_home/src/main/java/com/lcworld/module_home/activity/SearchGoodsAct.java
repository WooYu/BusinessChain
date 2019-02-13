package com.lcworld.module_home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.lcworld.module_home.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * 搜索商品
 */
public class SearchGoodsAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_search_goods);
        //隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

}
