package com.lcworld.module_system.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.databinding.ObservableList
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_backstage.model.VipMsgEntity
import com.lcworld.module_backstage.model.VipType
import com.lcworld.module_exchange.wrapSubscribe
import com.lcworld.module_system.ApiServiceInterf
import com.lcworld.module_system.R
import com.lcworld.module_system.viewmodel.recyclerItem.LevelItemViewModel
import me.goldze.mvvmhabit.BR
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class MemberViewModel(application: Application) : BaseViewModelEnhance(application) {
    val defaultList = listOf(
        LevelItemViewModel(this, "等级"),
        LevelItemViewModel(this, "白银"),
        LevelItemViewModel(this, "黄金"),
        LevelItemViewModel(this, "红钻"),
        LevelItemViewModel(this, "蓝钻"),
        LevelItemViewModel(this, "黑钻"),
        LevelItemViewModel(this, "成长值"),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "特权"),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "..."),
        LevelItemViewModel(this, "...")
    )
    val observableList: ObservableList<LevelItemViewModel<MemberViewModel>> =
        ObservableArrayList<LevelItemViewModel<MemberViewModel>>()
    val listSizeObservable = ObservableInt(observableList.size)
    val itemBinding: ItemBinding<LevelItemViewModel<MemberViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.system_item_level_list)
    val adapter = BindingRecyclerViewAdapter<LevelItemViewModel<MemberViewModel>>()
    val imageType =
        ObservableField<Drawable>(ContextCompat.getDrawable(application, R.mipmap.system_mem_silver_unselect))
    val memberType = ObservableField("")
    val fansNum = ObservableField("粉丝：0人")
    val name = ObservableField("")
    val invitationCode = ObservableField("")
    val invitationCodeStr = ObservableField("邀请码：${invitationCode.get()}")
    val growthValueMax = ObservableInt(0)
    val growthValueMaxStr = ObservableField("${growthValueMax.get()}")
    val growthValueProgress = ObservableInt(0)
    val growthValueTips = ObservableField("")
    val statusStr = ObservableField("")
    val doTaskOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val copyOnClickCommand = BindingCommand<Any>(BindingAction {
        if (invitationCode.get()!!.isEmpty()) {
            ToastUtils.showShort("没找到邀请码")
        } else {
            val clipboard =
                getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("chainNum", invitationCode.get())
            clipboard.primaryClip = clip
            ToastUtils.showShort("已复制")
        }
    })

    fun getVipMsg() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getVipMsg()
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(onNext = {
                val result = (it as RequestResult<VipMsgEntity>).data
                val levelItem = result.vip_list.find { item -> item.level == result.level }
                name.set(result.name)
                growthValueProgress.set(result.point.toIntOrNull() ?: 0)
                growthValueMax.set(result.point.toIntOrNull() ?: 0)
                growthValueMaxStr.set("${growthValueMax.get()}")
                statusStr.set(result.content1 + "\n" + result.content2)
                growthValueTips.set(result.introduce)
                invitationCode.set(result.invitation_code)
                invitationCodeStr.set("邀请码：${invitationCode.get()}")
                fansNum.set("粉丝：${result.num}人")
                if (levelItem == null) {
                    ToastUtils.showShort("配置错误，未找到该等级")
                }
                levelItem?.let { item ->
                    memberType.set(item.name)
                    growthValueMax.set(item.point.toIntOrNull() ?: 0)
                    growthValueMaxStr.set("${growthValueMax.get()}")
                    imageType.set(getImageType(result.point.toIntOrNull() ?: 0, item))
                }
                if (result.vip_list.isNotEmpty()) {
                    observableList.clear()
                    observableList.addAll(getList(result.vip_list))
                }
            }, onError = {
                observableList.clear()
                observableList.addAll(defaultList)
            })
    }

    private fun getImageType(point: Int, item: VipType): Drawable? {
        val itemPoint = item.point.toIntOrNull() ?: 0
        val resourceID: Int = when (item.level) {
            "2" -> if (point < itemPoint) R.mipmap.system_mem_gold_unselect else R.mipmap.system_mem_gold_select
            "3" -> if (point < itemPoint) R.mipmap.system_mem_red_unselect else R.mipmap.system_mem_red_select
            "4" -> if (point < itemPoint) R.mipmap.system_mem_bule_unselect else R.mipmap.system_mem_blue_select
            "5" -> if (point < itemPoint) R.mipmap.system_mem_black_unselect else R.mipmap.system_mem_black_select
            else -> if (point < itemPoint) R.mipmap.system_mem_silver_unselect else R.mipmap.system_mem_silver_select
        }
        return ContextCompat.getDrawable(this.getApplication(), resourceID)
    }

    private fun getList(list: List<VipType>): List<LevelItemViewModel<MemberViewModel>> {
        if (list.isEmpty()) return defaultList
        val nameList = list.map { LevelItemViewModel(this, it.name) }
        val pointList = list.map { LevelItemViewModel(this, it.point) }
        val powerList = list.map { LevelItemViewModel(this, it.power) }
        val typeList = arrayListOf<LevelItemViewModel<MemberViewModel>>()
        typeList.add(LevelItemViewModel(this, "等级"))
        typeList.addAll(nameList)
        typeList.add(LevelItemViewModel(this, "成长值"))
        typeList.addAll(pointList)
        typeList.add(LevelItemViewModel(this, "特权"))
        typeList.addAll(powerList)
        return typeList
    }

}