package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.ObservableField
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.activity.CheckStatusActivity
import com.lcworld.module_system.activity.CheckStatusActivity.Companion.STATUS_TYPE
import com.lcworld.module_system.activity.CheckStatusActivity.Companion.SUCCESS
import com.lcworld.module_system.activity.InfoInputActivity
import com.lcworld.module_system.fragment.CompanyInfoFrag
import com.lcworld.module_system.fragment.PersonInfoFrag
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class PersonInfoViewModel(application: Application) : BaseViewModelEnhance(application) {

    val submit = BindingCommand<Any>(BindingAction {
        val bundle = Bundle()
        bundle.putString(STATUS_TYPE, SUCCESS)
        startActivity(CheckStatusActivity::class.java, bundle)
    })

    /**
     * 对应条目点击事件  序号对应 页面条目位置
     */
    fun onItemClick(view: View) {
        val tag = view.tag.toString().toIntOrNull() ?: return
        when (tag) {
            PersonInfoFrag.INDEX_0 -> {
                ToastUtils.showShort("点击头像修改")
            }
            PersonInfoFrag.INDEX_1 -> gotoInfoInput("真实姓名", "输入真实姓名", CompanyInfoFrag.INDEX_1)
            PersonInfoFrag.INDEX_2 -> gotoInfoInput("身份证号", "输入身份证号", CompanyInfoFrag.INDEX_2)
            PersonInfoFrag.INDEX_3 -> gotoInfoInput("身份证照片", "输入身份证照片", CompanyInfoFrag.INDEX_3)
            else -> {
            }
        }
    }

    private fun gotoInfoInput(title: String, hint: String, type: Int) {
        val bundle = Bundle()
        bundle.putString(InfoInputActivity.TITLE, title)
        bundle.putString(InfoInputActivity.HINT, hint)
        bundle.putInt(InfoInputActivity.TYPE, type)
        startActivity(InfoInputActivity::class.java, bundle)
    }


}