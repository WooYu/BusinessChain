package com.lcworld.module_system.viewmodel.recyclerItem

import android.databinding.ObservableBoolean
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


class LevelItemViewModel<VM : BaseViewModel>(viewModel: VM, private val item: String = "",val isHead:Boolean = false) :
    ItemViewModel<VM>(viewModel) {
    val content = ObservableField<String>(item)
    val isTitle = ObservableBoolean(isHead)

}