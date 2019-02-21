package com.lcworld.library_base.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */

public class RouterActivityPath {
    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN +"/Main";
    }

    /**
     * 首页组件
     */
    public static class Home {
        private static final String Home = "/home";
        public static final String Pager_Home_Search = Home + "/search";
    }

    /**
     * 账号管理组件
     */
    public static class Account {
        private static final String ACCOUNT = "/account";
        //登录页面
        public static final String PAGER_LOGIN = ACCOUNT +"/login";
        //快速注册第一步
        public static final String PAGER_REGIST_FIRST = ACCOUNT +"/regist_first";

    }

    /**
     * 商品管理组件
     */
    public static class Product{
        private static final String PRODUCT = "/product";
        //商品详情
        public static final String PAGER_PRODUCTDETAIL = PRODUCT + "/detail";
    }

    /**
     * 订单管理组件
     */
    public static class Order {
        private static final String ORDER = "/order";
        public static final String Pager_Order_Confirm1 = ORDER + "/confirm1";
        public static final String Pager_Order_Confirm2 = ORDER + "/confirm2";
        public static final String PAGER_TROLLEY = ORDER + "/trolley";
        public static final String Pager_Payment_Choose = ORDER + "/payment/choose";
        public static final String Pager_Payment_Diamond = ORDER + "/payment/diamond";
        public static final String Pager_ReceiverAddress_List = ORDER + "/receiver/address/list";
        public static final String Pager_ReceiverAddress_Edit = ORDER + "/receiver/address/edit";
    }

    /**
     * 设置管理组件
     */
    public static class System {
        private static final String SYSTEM = "/system";
        public static final String PAGER_SYSTEM = SYSTEM + "/system";
    }

    /**
     * 后台管理组件
     */
    public static class Backstage {
        private static final String BACKSTAGE = "/backstage";
        public static final String PAGER_ENTRANCE = BACKSTAGE + "/backstage";
    }

    /**
     * 钱包组件
     */
    public static class Exchange {
        private static final String EXCHANGE = "/exchange";
        public static final String PAGER_WALLET = EXCHANGE + "/wallet";
    }
}
