package com.lcworld.library_base.config;

/**
 * Created by goldze on 2018/6/21 0021.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.lcworld.library_base.base.BaseModuleInit";
    //主业务模块
    private static final String MainInit = "com.lcworld.module_main.MainModuleInit";
    //首页模块
    private static final String HomeInit = "com.lcworld.module_home.HomeModuleInit";
    //商品管理模块
    private static final String GoodsInit = "com.lcworld.module_goods.GoodsModuleInit";
    //订单管理模块
    private static final String OrderInit = "com.lcworld.module_order.OrderModuleInit";
    //账号管理模块
    private static final String AccountInit = "com.lcworld.module_account.AccountModuleInit";
    //钱包模块
    private static final String ExchangeInit = "com.lcworld.module_exchange.ExchangeModuleInit";
    //后台模块
    private static final String BackstageInit = "com.lcworld.module_backstage.BackstageModuleInit";
    //系统设置模块
    private static final String SystemInit = "com.lcworld.module_system.SystemModuleInit";
    //分享模块
    private static final String ShareInit = "com.lcworld.module_share.ShareModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, HomeInit,GoodsInit,OrderInit,
            AccountInit,ExchangeInit,BackstageInit,SystemInit,ShareInit};

}
