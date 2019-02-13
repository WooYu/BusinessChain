package com.lcworld.module_share.debug;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.lcworld.module_share.R;

/**
 * 组件单独运行时的调试界面，不会被编译进release里
 * Created by goldze on 2018/6/21
 */


public class DebugActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity_main);
    }
}
