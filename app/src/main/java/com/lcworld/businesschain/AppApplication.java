package com.lcworld.businesschain;

import android.app.Application;
import com.blankj.utilcode.util.Utils;
import com.lcworld.library_base.config.ModuleLifecycleConfig;
import com.mob.MobSDK;
import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by goldze on 2018/6/21 0021.
 */

public class AppApplication extends BaseApplication {
    private static Application sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化AndroidUtilCode(注：MVVMHabit中引用了部分的AndroidUtilCode，在BaseApplication中也初始化了Utils)
        Utils.init(this);
        sInstance = this;
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
        //ShareSDK
        MobSDK.init(this);
    }

    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        } else {
            return sInstance;
        }
    }
}
