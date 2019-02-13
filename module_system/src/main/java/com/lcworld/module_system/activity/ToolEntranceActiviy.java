package com.lcworld.module_system.activity;

import android.os.Bundle;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.module_system.R;
import com.lcworld.module_system.databinding.SystemActivityEntranceBinding;
import me.goldze.mvvmhabit.BR;

/**
 * 工具
 */
public class ToolEntranceActiviy extends BaseActivityEnhance<SystemActivityEntranceBinding, BaseViewModelEnhance> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.system_activity_entrance;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
