package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;


public class MineViewModel extends BaseViewModelEnhance {
    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> loginName = new ObservableField("未登录");
    public ObservableField<String> chainid = new ObservableField("商链号：");
    public ObservableField<String> fans = new ObservableField("粉丝0人");
    public ObservableField<String> image = new ObservableField("");
    public BindingCommand walletOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Exchange.PAGER_WALLET).navigation();
        }
    });

    public BindingCommand backstageOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Backstage.PAGER_ENTRANCE).navigation();
        }
    });
    public BindingCommand toolOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Tool.PAGER_ENTRANCE).navigation();
        }
    });
    public BindingCommand settingOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.System.PAGER_SYSTEM).navigation();
        }
    });


}
