package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.*
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_backstage.model.QuestionItem
import com.lcworld.module_exchange.wrapSubscribe
import com.lcworld.module_system.ApiServiceInterf
import com.lcworld.module_system.R
import com.lcworld.module_system.viewmodel.recyclerItem.QuestionItemViewModel
import me.goldze.mvvmhabit.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class QuestionViewModel(application: Application) : BaseViewModelEnhance(application) {
    val observableList: ObservableList<QuestionItemViewModel<QuestionViewModel>> =
        ObservableArrayList<QuestionItemViewModel<QuestionViewModel>>()
    val itemBinding: ItemBinding<QuestionItemViewModel<QuestionViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.system_item_question_list)
    val adapter = BindingRecyclerViewAdapter<QuestionItemViewModel<QuestionViewModel>>()

    val showError = ObservableBoolean(false)

    fun loadData() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getProblems()
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = (it as RequestResult<List<QuestionItem>>).data
                result?.let { list ->
                    if (list.isEmpty()) {
                        showError.set(true)
                    } else {
                        observableList.addAll(list.map { item -> QuestionItemViewModel(this, item) })
                        showError.set(false)
                    }

                }
            }, onError = {
                ToastUtils.showShort(it.toString())
            })
    }
}