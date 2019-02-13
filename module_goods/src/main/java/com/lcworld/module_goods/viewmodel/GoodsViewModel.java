package com.lcworld.module_goods.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class GoodsViewModel extends BaseViewModelEnhance {
    //搜索框的点击事件
    public BindingCommand searchBoxClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            jump2SearchPage();
        }
    });

    public GoodsViewModel(@NonNull Application application) {
        super(application);
    }

    //跳转到搜索界面
    private void jump2SearchPage() {
        ToastUtils.showShort("路由到搜索界面");
    }
}
