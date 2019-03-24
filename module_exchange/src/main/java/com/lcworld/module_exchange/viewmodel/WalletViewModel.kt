package com.lcworld.module_exchange.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.*
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_exchange.ApiServiceInterf
import com.lcworld.module_exchange.activity.BillListAct
import com.lcworld.module_exchange.activity.RechargeAct
import com.lcworld.module_exchange.model.AccountBalance
import com.lcworld.module_exchange.wrapSubscribe
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import java.math.BigDecimal

class WalletViewModel(application: Application) : BaseViewModelEnhance(application) {
    val balance = ObservableField("￥0")
    val income = ObservableField("结算收益￥0")
    val rechargeOnClickCommand = BindingCommand<Any>(BindingAction { doRecharge() })
    val withDrawOnClickCommand = BindingCommand<Any>(BindingAction { doWithDraw() })
    val bankcardOnClickCommand = BindingCommand<Any>(BindingAction { bankcardItemClick() })
    val billOnClickCommand = BindingCommand<Any>(BindingAction { billItemClick() })
    val orderOnClickCommand = BindingCommand<Any>(BindingAction { orderItemClick() })
    val chainOnClickCommand = BindingCommand<Any>(BindingAction { chainItemClick() })

    //充值点击跳转
    private fun doRecharge() {
        startActivity(RechargeAct::class.java)
    }

    //提现点击跳转
    private fun doWithDraw() {
        ToastUtils.showShort("暂不支持此功能")
//        startActivity(WithDrawAct::class.java)
    }

    private fun bankcardItemClick() {
    }

    //账单点击跳转
    private fun billItemClick() {
        startActivity(BillListAct::class.java)
    }

    //订单点击跳转
    private fun orderItemClick() {
        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Review).navigation()
    }

    private fun chainItemClick() {

    }


    //请求余额
    @SuppressLint("CheckResult")
    fun requestBalance() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .requestBalance()
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = it as RequestResult<AccountBalance>
                val totlePrice = (result.data.totle_price.toBigDecimalOrNull() ?: BigDecimal.ZERO).stripTrailingZeros()
                    .toPlainString()
                val accountPrice =
                    (result.data.account_price.toBigDecimalOrNull() ?: BigDecimal.ZERO).stripTrailingZeros()
                        .toPlainString()
                balance.set("￥$totlePrice")
                income.set("结算收益￥$accountPrice")
            })
    }
}
