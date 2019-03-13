package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityFeedbackBinding
import com.lcworld.module_system.viewmodel.FeedBackViewModel
import me.goldze.mvvmhabit.BR

/**
 * 意见反馈
 */
class FeedBackActivity : BaseActivityEnhance<SystemActivityFeedbackBinding, FeedBackViewModel>() {
    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_feedback

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_tool)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
