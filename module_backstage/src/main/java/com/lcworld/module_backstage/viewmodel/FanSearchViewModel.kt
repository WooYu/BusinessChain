package com.lcworld.module_backstage.viewmodel

import android.app.Application
import android.databinding.*
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RetrofitClient
import com.lcworld.library_base.http.RxUtilsEnhanced
import com.lcworld.module_backstage.ApiServiceInterf
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.model.FansListEntity
import com.lcworld.module_backstage.viewmodel.recyclerItem.FanItemViewModel
import com.lcworld.module_exchange.wrapSubscribe
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

class FanSearchViewModel(application: Application) : BaseViewModelEnhance(application) {

    val searchCondition = ObservableField("")
    val observableList: ObservableList<FanItemViewModel<FanSearchViewModel>> =
        ObservableArrayList<FanItemViewModel<FanSearchViewModel>>()
    val itemBinding: ItemBinding<FanItemViewModel<FanSearchViewModel>> =
        ItemBinding.of(BR.viewModel, R.layout.back_item_fan_list)
    val adapter = BindingRecyclerViewAdapter<FanItemViewModel<FanSearchViewModel>>()
    val finishRefreshing = ObservableBoolean(false)
    val finishLoadMore = ObservableBoolean(false)
    private val pageNo = ObservableInt(1)
    val loadErrorStr = ObservableField("您还没有该粉丝，快去获取关注吧")
    val showError = ObservableBoolean(false)

    //下拉刷新
    fun doRefresh() {
        pageNo.set(1)
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getFansList(page_no = pageNo.get(), mobile = searchCondition.get() ?: "")
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(
                onNext = {
                    val result = (it as RequestResult<FansListEntity>).data
                    observableList.clear()
                    observableList.addAll(result.list.map { item -> FanItemViewModel(this, item) })
                },
                onError = {
                    finishRefreshing.set(!finishRefreshing.get())
                    showError.set(true)
                    ToastUtils.showShort("数据加载失败")
                },
                onComplete = {
                    showError.set(observableList.isEmpty())
                    finishRefreshing.set(!finishRefreshing.get())
                })
    }

    //上拉加载
    fun loadMore() {
        RetrofitClient.getInstance().create<ApiServiceInterf>(ApiServiceInterf::class.java)
            .getFansList(page_no = pageNo.get() + 1, mobile = searchCondition.get() ?: "")
            .compose(RxUtilsEnhanced.explicitTransform())
            .wrapSubscribe(
                onNext = {
                    val result = (it as RequestResult<FansListEntity>).data
                    if (result.list.isNotEmpty()) {
                        pageNo.set(result.page_no)
                        observableList.addAll(result.list.map { item -> FanItemViewModel(this, item) })
                    } else {
                        ToastUtils.showShort("没有更多数据了")
                    }
                },
                onError = {
                    finishLoadMore.set(!finishLoadMore.get())
                    ToastUtils.showShort("数据加载失败")
                },
                onComplete = {
                    finishLoadMore.set(!finishLoadMore.get())
                })
    }


}