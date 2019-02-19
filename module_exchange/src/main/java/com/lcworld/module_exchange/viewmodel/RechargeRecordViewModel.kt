package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.viewmodel.recyclerItem.MultiItemViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.RechargeRecordItemContentViewModel
import com.lcworld.module_exchange.viewmodel.recyclerItem.RechargeRecordItemHeadViewModel
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RechargeRecordViewModel(application: Application) : BaseViewModelEnhance(application) {
    companion object {
        const val TYPE_HEAD = 0
        const val TYPE_ITEM = 1
    }

    val observableList: ObservableList<MultiItemViewModel<RechargeRecordViewModel>> =
        ObservableArrayList<MultiItemViewModel<RechargeRecordViewModel>>()
    //RecyclerView多布局添加ItemBinding
    val itemBinding: ItemBinding<MultiItemViewModel<RechargeRecordViewModel>> = ItemBinding.of { itemBinding, _, item ->
        //通过item的类型, 动态设置Item加载的布局
        when (item.itemType as Int) {
            TYPE_HEAD -> itemBinding.set(BR.viewModel, R.layout.exchange_item_record_head)
            else -> itemBinding.set(BR.viewModel, R.layout.exchange_item_record_content)
        }

    }
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    val adapter = BindingRecyclerViewAdapter<MultiItemViewModel<RechargeRecordViewModel>>()

    override fun onCreate() {
        super.onCreate()
        val head = RechargeRecordItemHeadViewModel(this, "2019年1月").apply { itemType = TYPE_HEAD }
        val content = RechargeRecordItemContentViewModel(this).apply { itemType = TYPE_ITEM }
        observableList.add(head)
        observableList.add(content)
        observableList.add(head)
        observableList.add(content)
        observableList.add(content)

    }
}