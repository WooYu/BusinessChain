package com.lcworld.module_home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lcworld.module_home.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * 商品热销列表
 */
public class GoodsHotSaleListAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_search_goodsresult);

        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

}
