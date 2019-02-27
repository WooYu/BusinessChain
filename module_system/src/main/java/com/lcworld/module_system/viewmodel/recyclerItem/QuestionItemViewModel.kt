package com.lcworld.module_system.viewmodel.recyclerItem

import android.databinding.ObservableField
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand


class QuestionItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    val questionType1 = ObservableField("计佣")
    val questionType2 = ObservableField("规则")
    val questionItem1 = ObservableField("商链佣金结算扣税规则？")
    val questionItem2 = ObservableField("商链佣金计算规则?")
    val item1OnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val item2OnClickCommand = BindingCommand<Any>(BindingAction {
    })

}