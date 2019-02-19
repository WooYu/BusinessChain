package com.lcworld.module_exchange.viewmodel.recyclerItem

import android.databinding.ObservableField
import com.lcworld.module_exchange.viewmodel.RechargeRecordViewModel


class RechargeRecordItemHeadViewModel(viewModel: RechargeRecordViewModel, text: String) :
    MultiItemViewModel<RechargeRecordViewModel>(viewModel) {

    val text = ObservableField("")

    init {
        this.text.set(text)
    }
}