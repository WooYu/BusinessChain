package com.lcworld.module_backstage;

import android.app.Application;
import com.lcworld.library_base.base.IModuleInit;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 后台模块
 */

public class BackstageModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("后台模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("后台模块初始化 -- onInitLow");
        return false;
    }
}
