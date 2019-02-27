package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityEntranceBinding
import me.goldze.mvvmhabit.BR

/**
 * 工具
 */
class ToolEntranceActiviy : BaseActivityEnhance<SystemActivityEntranceBinding, BaseViewModelEnhance>() {
    override fun initContentView(bundle: Bundle): Int {
        return R.layout.system_activity_entrance
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
