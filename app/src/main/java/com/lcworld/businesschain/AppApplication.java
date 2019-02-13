package com.lcworld.businesschain;

import com.blankj.utilcode.util.Utils;
import com.lcworld.library_base.config.ModuleLifecycleConfig;
import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by goldze on 2018/6/21 0021.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化AndroidUtilCode(注：MVVMHabit中引用了部分的AndroidUtilCode，在BaseApplication中也初始化了Utils)
        Utils.init(this);

        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
