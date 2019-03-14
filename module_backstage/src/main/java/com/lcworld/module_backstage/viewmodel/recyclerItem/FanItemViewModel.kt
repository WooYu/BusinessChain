package com.lcworld.module_backstage.viewmodel.recyclerItem

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import com.lcworld.module_backstage.activity.FanListActivity
import com.lcworld.module_backstage.activity.FansDetailActivity
import com.lcworld.module_backstage.activity.FansDetailActivity.Companion.FANS_DETAIL
import com.lcworld.module_backstage.model.FansItem
import com.lcworld.module_exchange.TimeUtil
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import java.util.*


class FanItemViewModel<VM : BaseViewModel>(viewModel: VM, item: FansItem = FansItem()) : ItemViewModel<VM>(viewModel) {
    val isDirect = ObservableBoolean(false)
    val date = ObservableField("")
    val name = ObservableField(item.uname ?: "")
    val phone = ObservableField("")
    val faceUrl = ObservableField(item.face)
    val itemOnClickCommand = BindingCommand<Any>(BindingAction {
        val bundle = Bundle()
        bundle.putParcelable(FANS_DETAIL, item)
        viewModel.startActivity(FansDetailActivity::class.java, bundle)
    })

    init {
        val time = item.create_time?.let {
            it.toLongOrNull()?.let { t ->
                TimeUtil.formatData(Date().apply {
                    time = t.times(1000)
                }, "yyyy-MM-dd HH:mm")
            } ?: ""
        } ?: ""
        date.set(time)
        phone.set("手机号：${item.mobile}")
    }

}