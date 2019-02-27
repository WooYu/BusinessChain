package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class QuestionDetailViewModel(application: Application) : BaseViewModelEnhance(application) {

    val title = ObservableField("商链佣金结算扣税规则？")
    val content = ObservableField(
        "佣金＝有效交易金额× 佣金比例\n" +
                "有效交易金额：用户经由开发者应用中展示的点评团购链\n" +
                "接来到点评进行团购消费、并确认到店使用的团购订单金\n" +
                "额佣金比例：大众点评根据行业整体标准制定的指标，会\n" +
                "根据不同团购单实际情况灵活调整。（点击查看当前佣金\n" +
                "比例）\n" +
                "\n" +
                "例如：开发者在自己应用中展示了一个价格为168元的餐\n" +
                "厅团购链接，用户点击链接，来到点评下单购买， 并最终\n" +
                "到店使用，结算时该团购单的佣金比例为3%， 那么开发\n" +
                "者在这笔已完成的交易中可以获取的佣金是：\n" +
                "\n" +
                "168× 3%＝4.89元"
    )


}