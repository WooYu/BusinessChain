package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ObservableList
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.viewmodel.recyclerItem.LevelItemViewModel
import me.goldze.mvvmhabit.BR
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class MemberViewModel(application: Application) : BaseViewModelEnhance(application) {
    val observableList: ObservableList<LevelItemViewModel<MemberViewModel>> =
        ObservableArrayList<LevelItemViewModel<MemberViewModel>>()
    val itemBinding: ItemBinding<LevelItemViewModel<MemberViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.system_item_level_list)
    val adapter = BindingRecyclerViewAdapter<LevelItemViewModel<MemberViewModel>>()
    val growthValueMax = ObservableInt(1000)
    val inviteOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val copyOnClickCommand = BindingCommand<Any>(BindingAction {
    })

    override fun onCreate() {
        super.onCreate()
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsds"))
        observableList.add(LevelItemViewModel(this,"sdsdsdssssssssss"))
    }

}