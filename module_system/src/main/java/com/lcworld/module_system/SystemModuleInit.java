package com.lcworld.module_system;

import android.app.Application;
import com.lcworld.library_base.base.IModuleInit;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by goldze on 2018/6/21 0021.
 */

public class SystemModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("系统设置模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("系统设置模块初始化 -- onInitLow");
        return false;
    }
}
