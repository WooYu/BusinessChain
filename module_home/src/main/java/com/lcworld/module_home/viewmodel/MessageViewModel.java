package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;

public class MessageViewModel extends BaseViewModelEnhance {

    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    //点击返回键
    public BindingCommand clickBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //点击收益消息
    public final BindingCommand checkIncomeMsg = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean aBoolean) {

        }
    });


    //点击其它消息
    public final BindingCommand checkOtherMsg = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean aBoolean) {

        }
    });
}
