package com.lcworld.module_system.activity

import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityQuestionDetailBinding
import com.lcworld.module_system.viewmodel.QuestionDetailViewModel
import me.goldze.mvvmhabit.BR

/**
 * 常见问题
 */
class QuestionDetailActivity : BaseActivityEnhance<SystemActivityQuestionDetailBinding, QuestionDetailViewModel>() {
    companion object {
        const val TITLE = "title"
        const val CONTENT = "content"
    }

    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_question_detail

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_question)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        viewModel.title.set(intent.getStringExtra(TITLE) ?: "")
        viewModel.content.set(intent.getStringExtra(CONTENT) ?: "")
    }
}
