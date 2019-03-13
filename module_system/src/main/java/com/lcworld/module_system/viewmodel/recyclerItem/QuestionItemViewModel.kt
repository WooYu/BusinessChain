package com.lcworld.module_system.viewmodel.recyclerItem

import android.databinding.ObservableField
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.module_backstage.model.QuestionItem
import com.lcworld.module_system.activity.QuestionDetailActivity
import com.lcworld.module_system.activity.QuestionDetailActivity.Companion.CONTENT
import com.lcworld.module_system.activity.QuestionDetailActivity.Companion.TITLE
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand


class QuestionItemViewModel<VM : BaseViewModel>(viewModel: VM, private val item: QuestionItem) :
    ItemViewModel<VM>(viewModel) {
    val questionType = ObservableField<SpannableString>()
    val questionItem1 = ObservableField("")
    val questionItem2 = ObservableField("")
    val item1OnClickCommand = BindingCommand<Any>(BindingAction {
        if (item.problems.isNotEmpty()) {
            val bundle = Bundle()
            bundle.putString(TITLE, item.problems[0].problem)
            bundle.putString(CONTENT, item.problems[0].content)
            viewModel.startActivity(QuestionDetailActivity::class.java, bundle)
        } else {
            ToastUtils.showShort("详情数据缺失")
        }

    })

    val item2OnClickCommand = BindingCommand<Any>(BindingAction {
        if (item.problems.size > 1) {
            val bundle = Bundle()
            bundle.putString(TITLE, item.problems[1].problem)
            bundle.putString(CONTENT, item.problems[1].content)
            viewModel.startActivity(QuestionDetailActivity::class.java, bundle)
        } else {
            ToastUtils.showShort("详情数据缺失")
        }
    })

    init {
        val str = if (item.title.length > 2) getSpecialText(item.title) else SpannableString(item.title)
        questionType.set(str)
        if (item.problems.isNotEmpty()) {
            questionItem1.set(item.problems[0].problem)
        }
        if (item.problems.size > 1) {
            questionItem2.set(item.problems[1].problem)
        }
    }

    private fun getSpecialText(str: String): SpannableString {
        val text = SpannableString(str)
        text.setSpan(
            ForegroundColorSpan(Color.parseColor("#333333")),
            0,
            text.length - 2,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return text
    }

}