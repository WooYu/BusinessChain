package com.lcworld.module_backstage.viewmodel

import android.app.Application
import android.databinding.*
import android.os.Bundle
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_backstage.ApiServiceInterf
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.activity.FanListActivity
import com.lcworld.module_backstage.activity.FanListActivity.Companion.FANS_TYPE
import com.lcworld.module_backstage.model.BackIndexEntity
import com.lcworld.module_backstage.model.FansCharItem
import com.lcworld.module_backstage.viewmodel.recyclerItem.FanItemViewModel
import com.lcworld.module_exchange.wrapSubscribe
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class BackEntranceViewModel(application: Application) : BaseViewModelEnhance(application) {
    val fansCharList: ArrayList<FansCharItem> = arrayListOf()
    val getfansCharData = ObservableBoolean(false)
    val allFan = ObservableField<String>("")
    val directFan = ObservableField<String>("")
    val indirectFan = ObservableField<String>("")
    val observableList: ObservableList<FanItemViewModel<BackEntranceViewModel>> =
        ObservableArrayList<FanItemViewModel<BackEntranceViewModel>>()
    val itemBinding: ItemBinding<FanItemViewModel<BackEntranceViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.back_item_fan_list)
    val adapter = BindingRecyclerViewAdapter<FanItemViewModel<BackEntranceViewModel>>()
    val allOnClickCommand = BindingCommand<Any>(BindingAction {
        gotoFansList(0)
    })


    val directOnClickCommand = BindingCommand<Any>(BindingAction {
        gotoFansList(1)
    })
    val indirectOnClickCommand = BindingCommand<Any>(BindingAction {
        gotoFansList(2)
    })

    private fun gotoFansList(type: Int) {
        val bundle = Bundle()
        bundle.putInt(FANS_TYPE, type)
        startActivity(FanListActivity::class.java, bundle)
    }

    fun loadData() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .loadFansIndexData()
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = (it as RequestResult<BackIndexEntity>).data
                result?.let { entity ->
                    allFan.set(entity.total_count)
                    directFan.set(entity.direct_count)
                    indirectFan.set(entity.in_direct_count)
                    if (!entity.day_list.isNullOrEmpty()) {
                        fansCharList.clear()
                        fansCharList.addAll(entity.day_list)
                        getfansCharData.set(true)
                    }
                    observableList.addAll(entity.members.map { item -> FanItemViewModel(this, item) })
                }

            })
    }
}