package com.lcworld.module_exchange.viewmodel.recyclerItem

import android.databinding.ObservableField
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel


class BankCardListItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    val bankname = ObservableField("中国邮政储蓄银行")
    val cardNumber = ObservableField("621700*********6341")

}