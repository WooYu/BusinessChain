package com.lcworld.module_exchange.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 账户余额
 */
data class AccountBalance(
    val id: Int = 0,
    val member_id: String = "",
    val totle_price: String = "",
    val account_price: String = ""
)
data class RechargeResult(val form_items:List<RechargeItem> = listOf(),val gateway_url:String = "")
data class RechargeItem(val item_name:String = "",val item_value:String = "")
data class BillListEntity(
    val bill_list: BillListData = BillListData(),
    val pay_money: Double = 0.0,
    val income_money: Double = 0.0
)

data class BillListData(
    val data: List<BillListItem> = listOf(), val data_total: String = ""
    , val page_no: Int = 1, val page_size: String = ""
)
@Parcelize
data class BillListItem(
    val create_time: Long = 0, val id: String = "", val member_id: String = "", val order_name: String = "",
    val order_sn: String = "", val pay_method: String = "", val pay_money: Double = 0.00, val pay_type: Int = 1,
    val three_order_sn: String = "", val type: Int = 11
) : Parcelable
