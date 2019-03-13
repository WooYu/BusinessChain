package com.lcworld.module_system.viewmodel

import android.app.Application
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.activity.CheckStatusActivity
import com.lcworld.module_system.activity.InfoInputActivity
import com.lcworld.module_system.activity.InfoInputActivity.Companion.HINT
import com.lcworld.module_system.activity.InfoInputActivity.Companion.TITLE
import com.lcworld.module_system.activity.InfoInputActivity.Companion.TYPE
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_0
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_1
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_2
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_3
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_4
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_5
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_6
import com.lcworld.module_system.fragment.CompanyInfoFrag.Companion.INDEX_7
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class CompanyInfoViewModel(application: Application) : BaseViewModelEnhance(application) {
    val submit = BindingCommand<Any>(BindingAction {
        val bundle = Bundle()
        bundle.putString(CheckStatusActivity.STATUS_TYPE, CheckStatusActivity.FAIL)
        startActivity(CheckStatusActivity::class.java, bundle)
    })

    /**
     * 对应条目点击事件  序号对应 页面条目位置
     */
    fun onItemClick(view: View) {
        val tag = view.tag.toString().toIntOrNull() ?: return
        when (tag) {
            INDEX_0 -> {
                ToastUtils.showShort("点击头像修改")
            }
            INDEX_1 -> gotoInfoInput("企业名称", "输入企业名称", INDEX_1)
            INDEX_2 -> gotoInfoInput("所属行业", "输入所属行业", INDEX_2)
            INDEX_3 -> gotoInfoInput("联系人", "输入联系人", INDEX_3)
            INDEX_4 -> gotoInfoInput("职务", "输入职务", INDEX_4)
            INDEX_5 -> gotoInfoInput("手机号", "输入手机号", INDEX_5)
            INDEX_6 -> gotoInfoInput("统一信用代码", "输入统一信用代码", INDEX_6)
            INDEX_7 -> gotoInfoInput("办公地址", "输入办公地址", INDEX_7)
            else -> {
            }
        }
    }

    private fun gotoInfoInput(title: String, hint: String, type: Int) {
        val bundle = Bundle()
        bundle.putString(TITLE, title)
        bundle.putString(HINT, hint)
        bundle.putInt(TYPE, type)
        startActivity(InfoInputActivity::class.java, bundle)
    }


}