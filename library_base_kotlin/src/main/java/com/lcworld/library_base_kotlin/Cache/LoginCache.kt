package com.lcworld.library_base_kotlin.Cache

import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import com.lcworld.library_base.global.SPKeyGlobal
import com.lcworld.library_base.model.DataLogin

object LoginCache {
    fun isLogin(): Boolean = !getLoginData()?.access_token.isNullOrEmpty()

    fun getLoginData(): DataLogin? {
        val loginInfo = SPUtils.getInstance().getString(SPKeyGlobal.KEY_LOGIN_INFO)
        return Gson().fromJson<DataLogin>(loginInfo, DataLogin::class.java)
    }
}