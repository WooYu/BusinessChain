package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.*
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.R
import com.lcworld.module_system.viewmodel.recyclerItem.QuestionItemViewModel
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class QuestionViewModel(application: Application) : BaseViewModelEnhance(application) {
    val observableList: ObservableList<QuestionItemViewModel<QuestionViewModel>> =
        ObservableArrayList<QuestionItemViewModel<QuestionViewModel>>()
    //RecyclerView多布局添加ItemBinding
    val itemBinding: ItemBinding<QuestionItemViewModel<QuestionViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.system_item_question_list)
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    val adapter = BindingRecyclerViewAdapter<QuestionItemViewModel<QuestionViewModel>>()

    override fun onCreate() {
        super.onCreate()
        observableList.add(QuestionItemViewModel(this))
        observableList.add(QuestionItemViewModel(this))
        observableList.add(QuestionItemViewModel(this))
    }
}