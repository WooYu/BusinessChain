package com.lcworld.module_exchange.viewmodel.recyclerItem

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lcworld.module_exchange.R
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel


class BillListItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    val payTypeText = ObservableField("微信支付")
    val amount = ObservableField("-100")
    val date = ObservableField("1月21日 16:22")
    val isIncome = ObservableBoolean(false)
    val payTextImage = ObservableInt(R.mipmap.exchange_weixin)

}