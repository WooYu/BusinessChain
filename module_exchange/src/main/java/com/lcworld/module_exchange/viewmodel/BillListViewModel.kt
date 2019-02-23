package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.viewmodel.recyclerItem.BillListItemViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.MultiItemViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.RechargeRecordItemContentViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.RechargeRecordItemHeadViewModel
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class BillListViewModel(application: Application) : BaseViewModelEnhance(application) {

    val observableList: ObservableList<BillListItemViewModel<BillListViewModel>> =
        ObservableArrayList<BillListItemViewModel<BillListViewModel>>()
    //RecyclerView多布局添加ItemBinding
    val itemBinding: ItemBinding<BillListItemViewModel<BillListViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.exchange_item_bill_list)
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    val adapter = BindingRecyclerViewAdapter<BillListItemViewModel<BillListViewModel>>()

    val date = ObservableField("2019年2月")
    val totalIncome = ObservableField("支出￥35486.74  收入2441.51")
}