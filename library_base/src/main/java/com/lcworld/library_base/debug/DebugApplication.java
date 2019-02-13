package com.lcworld.library_base.debug;

import com.blankj.utilcode.util.Utils;
import com.lcworld.library_base.config.ModuleLifecycleConfig;
import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * Created by goldze on 2018/6/25 0025.
 * debug包下的代码不参与编译，仅作为独立模块运行时初始化数据
 */

public class DebugApplication extends BaseApplication {
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
