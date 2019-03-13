package com.lcworld.module_exchange.viewmodel.recyclerItem

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.model.BillListItem
import com.lcworld.module_exchange.viewmodel.BillListViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import java.util.*


class BillListItemViewModel(viewModel: BillListViewModel, val item: BillListItem) :
    ItemViewModel<BillListViewModel>(viewModel) {
    val payTypeText = ObservableField(item.pay_method)
    val amount = ObservableField("")
    val date = ObservableField("")
    val isIncome: ObservableBoolean = ObservableBoolean(false)
    val payTextImage = ObservableField<Drawable>()

    init {
        //1增 2减
        val payMoney = TimeUtil.formatAmount(item.pay_money)
        val amountStr = if (item.pay_type == 1) "+$payMoney" else "-$payMoney"
        amount.set(amountStr)
        isIncome.set(item.pay_type == 1)
        //11支付宝 22微信 2商币 3余额
        val image = when (item.type) {
            11 -> R.mipmap.exchange_alipay
            else -> R.mipmap.exchange_weixin
        }
        payTextImage.set(ContextCompat.getDrawable(viewModel.getApplication(), image))
        payTypeText.set(item.pay_method)
        date.set(TimeUtil.formatData(Date(item.create_time.times(1000)), "MM月dd日 HH:mm"))
    }

}