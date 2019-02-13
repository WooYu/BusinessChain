package com.lcworld.module_exchange;

import android.app.Application;
import com.lcworld.library_base.base.IModuleInit;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by goldze on 2018/6/21 0021.
 */

public class ExchangeModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("商链钻模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("商链钻模块初始化 -- onInitLow");
        return false;
    }
}
