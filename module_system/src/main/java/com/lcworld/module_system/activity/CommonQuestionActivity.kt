package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityQuestionBinding
import com.lcworld.module_system.viewmodel.QuestionViewModel
import me.goldze.mvvmhabit.BR

/**
 * 常见问题
 */
class CommonQuestionActivity : BaseActivityEnhance<SystemActivityQuestionBinding, QuestionViewModel>() {
    override fun initContentView(bundle: Bundle): Int = R.layout.system_activity_question

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_question)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}
