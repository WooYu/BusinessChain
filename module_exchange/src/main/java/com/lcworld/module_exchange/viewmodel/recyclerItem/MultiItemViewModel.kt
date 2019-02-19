package com.lcworld.module_exchange.viewmodel.recyclerItem

import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel

open class MultiItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    var itemType: Any = Any()
}