package com.lcworld.module_account;

import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.library_base.model.DataLogin;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface ApiServiceInterf {
    //会员找回密码API
    //发送验证码
    @POST("passport/find-pwd/smscode/{mobile}")
    Observable<RequestResultImp> findpswSmscode(@Path("mobile") String mobile);

    //验证手机验证码
    @POST("passport/find-pwd/smscode/{mobile}/check")
    Observable<RequestResultImp> findpswSmscodeCheck(@Path("mobile") String mobile, @Query("code") String code);

    //修改密码
    @PUT("passport/find-pwd/update-password/{mobile}")
    Observable<RequestResultImp> findpswUpdatePsw(@Path("mobile") String mobile, @Query("password") String password);

    //会员等API
    //手机号/密码登录API
    @POST("passport/login")
    Observable<RequestResult<DataLogin>> loginModeA(@Query("mobile") String mobile, @Query("password") String password);

    //发送验证码
    @POST("passport/login/smscode/{mobile}")
    Observable<RequestResultImp> loginSmscode(@Path("mobile") String mobile);

    //手机号码登录API
    @POST("passport/login/{mobile}")
    Observable<RequestResult<DataLogin>> loginModeB(@Path("mobile") String mobile, @Query("sms_code") String sms_code);

    //会员注册API
    //app注册
    @POST("passport/register/app")
    Observable<RequestResultImp> registerApp(@Query("recommend_code") String recommendCode, @Query("mobile") String mobile, @Query("password") String password);

    //发送验证码
    @POST("passport/register/smscode/{mobile}")
    Observable<RequestResultImp> registerSmscode(@Path("mobile") String mobile);

    //验证手机验证码
    @POST("passport/register/smscode/{mobile}/check")
    Observable<RequestResultImp> registerSmscodeCheck(@Path("mobile") String mobile, @Query("code") String code, @Query("recommend_code") String recommend_code);

    //会员
    //会员安全API
    //判断支付密码
    @POST("members/security/pay/check")
    Observable<RequestResultImp> membersPayPswCheck(@Query("password") String password);

    //验证支付密码验证码
    @GET("members/security/pay/password")
    Observable<RequestResultImp> membersPayAuthCodeCheck(@Query("code") String code);

    //发送支付密码验证码
    @POST("members/security/pay/send/{mobile}")
    Observable<RequestResultImp> membersPayAuthCodeSend(@Path("mobile") String mobile);

    //修改支付密码
    @POST("members/security/pay/update")
    Observable<RequestResultImp> membersPayPswUpdate(@Query("password") String password, @Query("code") String code);
}
