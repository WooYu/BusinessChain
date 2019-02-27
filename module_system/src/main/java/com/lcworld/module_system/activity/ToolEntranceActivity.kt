package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityEntranceBinding
import com.lcworld.module_system.viewmodel.ToolEntranceViewModel
import me.goldze.mvvmhabit.BR

/**
 * 工具
 */
class ToolEntranceActivity : BaseActivityEnhance<SystemActivityEntranceBinding, ToolEntranceViewModel>() {
    override fun initContentView(bundle: Bundle): Int = R.layout.system_activity_entrance

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_tool)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
