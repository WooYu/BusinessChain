package com.lcworld.module_backstage.viewmodel

import android.app.Application
import android.databinding.*
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.viewmodel.recyclerItem.FanItemViewModel
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class FanSearchViewModel(application: Application) : BaseViewModelEnhance(application) {

    val observableList: ObservableList<FanItemViewModel<FanSearchViewModel>> =
        ObservableArrayList<FanItemViewModel<FanSearchViewModel>>()
    //RecyclerView多布局添加ItemBinding
    val itemBinding: ItemBinding<FanItemViewModel<FanSearchViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.back_item_fan_list)
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    val adapter = BindingRecyclerViewAdapter<FanItemViewModel<FanSearchViewModel>>()

    override fun onCreate() {
        super.onCreate()
        observableList.add(FanItemViewModel(this))
        observableList.add(FanItemViewModel(this))
        observableList.add(FanItemViewModel(this))
    }


}