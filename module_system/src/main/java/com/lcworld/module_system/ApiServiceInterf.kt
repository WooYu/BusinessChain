package com.lcworld.module_system

import com.lcworld.library_base.http.RequestResult
import com.lcworld.library_base.http.RequestResultImp
import com.lcworld.module_backstage.model.QuestionItem
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServiceInterf {

    /**
     * 系统文本获取
     * type 0拼团客服电话 10关于我们 20拼团合同 30拼团支付协议 40 常见问题 50注册协议 60成长值介绍
     */
    @GET("sys/txt")
    fun loadText(@Query("type") type: String): Observable<RequestResultImp>

    /**
     * 添加意见反馈
     * content 内容
     * image 图片地址 逗号分隔
     */
    @GET("sys/feedBack")
    fun getFansDetail(@Query("content") content: String, @Query("image") image: String): Observable<RequestResultImp>

    /**
     *  获取问题列表
     */
    @GET("ucenter/tool/getProblems")
    fun getProblems(): Observable<RequestResult<List<QuestionItem>>>

}
