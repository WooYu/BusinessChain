package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.viewmodel.recyclerItem.BankCardListItemViewModel
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding


class BankCardListViewModel(application: Application) : BaseViewModelEnhance(application) {

    val observableList: ObservableList<BankCardListItemViewModel<BankCardListViewModel>> =
        ObservableArrayList<BankCardListItemViewModel<BankCardListViewModel>>()
    //RecyclerView多布局添加ItemBinding
    val itemBinding: ItemBinding<BankCardListItemViewModel<BankCardListViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.exchange_item_bankcard_list)
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    val adapter = BindingRecyclerViewAdapter<BankCardListItemViewModel<BankCardListViewModel>>()

    override fun onCreate() {
        super.onCreate()
        observableList.add(BankCardListItemViewModel(this))
        observableList.add(BankCardListItemViewModel(this))
    }
}