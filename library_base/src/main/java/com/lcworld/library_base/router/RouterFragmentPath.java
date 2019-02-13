package com.lcworld.library_base.router;

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */

public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_ENTRANCE = HOME + "/entrance";
        /*会员专区*/
        public static final String PAGER_MEMBERAREA = HOME + "/member";
        /*我的*/
        public static final String PAGER_MINE = HOME + "/mine";
    }

    /**
     * 商品管理
     */
    public static class Goods {
        private static final String GOODS = "/goods";
        public static final String PAGER_ENTRANCE = GOODS + "/entrance";
    }

    /**
     * 账号管理组件
     */
    public static class Account {
        private static final String ACCOUNT = "/account";
    }

    /**
     * 钱包
     */
    public static class Exchange {
        private static final String EXCHANGE = "/exchange";
        public static final String PAGER_ENTRANCE = EXCHANGE + "/entrance";
    }

    /**
     * 分享
     */
    public static class Share {
        private static final String SHARE = "/share";
        public static final String PAGER_ENTRANCE = SHARE + "/entrance";

    }

}
