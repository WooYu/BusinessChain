package com.lcworld.module_backstage

import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RequestResultImp
import com.lcworld.module_backstage.model.BackIndexEntity
import com.lcworld.module_backstage.model.FansItem
import com.lcworld.module_backstage.model.FansListEntity
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServiceInterf {
    //后台
    //首页
    @GET("fans/index")
    fun loadFansIndexData(): Observable<RequestResult<BackIndexEntity>>

    //粉丝详情
    @GET("fans/getDetail")
    fun getFansDetail(@Query("uid") uid: String): Observable<RequestResultImp>

    /**
     *  粉丝详情 disabled 会员状态,0为正常.-1为删除，在会员回收站中
     */
    @GET("fans")
    fun getFansList(
        @Query("uname") uname: String = "", @Query("email") email: String = "",
        @Query("mobile") mobile: String = "", @Query("sex") sex: String = "",
        @Query("region") region: String = "", @Query("start_time") start_time: String = "",
        @Query("end_time") end_time: String = "", @Query("keyword") keyword: String = "",
        @Query("disabled") disabled: Int = 0, @Query("fansType") fansType: Int = 0,
        @Query("memberId") memberId: Int = 0, @Query("page_no") page_no: Int = 1,
        @Query("page_size") page_size: Int = 10
    ): Observable<FansListEntity>

}
