package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityAboutusBinding
import me.goldze.mvvmhabit.BR

/**
 * 邀请好友
 */
class AboutUsActivity : BaseActivityEnhance<SystemActivityAboutusBinding, BaseViewModelEnhance>() {
    override fun initContentView(bundle: Bundle): Int = R.layout.system_activity_aboutus

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_about_us)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
