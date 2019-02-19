package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.lcworld.module_home.bean.DataSpellDeals;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class MineViewModel extends BaseViewModelEnhance {
    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand walletOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Exchange.PAGER_WALLET).navigation();
        }
    });
}
