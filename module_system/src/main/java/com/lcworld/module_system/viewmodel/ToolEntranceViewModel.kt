package com.lcworld.module_system.viewmodel

import android.app.Application
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_system.activity.AboutUsActivity
import com.lcworld.module_system.activity.CommonQuestionActivity
import com.lcworld.module_system.activity.FeedBackActivity
import com.lcworld.module_system.activity.InviteFriendActivity
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class ToolEntranceViewModel(application: Application) : BaseViewModelEnhance(application) {

    val shareOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(InviteFriendActivity::class.java)
    })
    val questionOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(CommonQuestionActivity::class.java)
    })
    val feedbackOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(FeedBackActivity::class.java)
    })
    val aboutUsOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(AboutUsActivity::class.java)
    })

}