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
     * 订单管理组件
     */
    public static class Order {
        private static final String ORDER = "/order";
        public static final String PAGER_ORDER = ORDER + "/order";
        public static final String PAGER_TROLLEY = ORDER + "/trolley";
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
}
