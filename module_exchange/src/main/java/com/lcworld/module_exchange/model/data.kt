package com.lcworld.module_exchange.model

/**
 * 账户余额
 */
data class AccountBalance(
    val id: Int = 0,
    val member_id: String = "",
    val totle_price: String = "",
    val account_price: String = ""
)