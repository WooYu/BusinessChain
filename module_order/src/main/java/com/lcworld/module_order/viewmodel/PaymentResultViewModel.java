package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.EventPayResult;
import com.lcworld.library_base.router.RouterActivityPath;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;

public class PaymentResultViewModel extends BaseViewModelEnhance {
    public final ObservableField<String> valueOfPayResultTip = new ObservableField<>();
    public final ObservableBoolean valueOfPayResult = new ObservableBoolean();
    public final BindingCommand clickOfSeeLater = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxBus.getDefault().post(new EventPayResult(valueOfPayResult.get()));
            finish();
        }
    });

    public final BindingCommand clickOfViewOrder = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxBus.getDefault().post(new EventPayResult(valueOfPayResult.get()));
            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Review).navigation();
            finish();
        }
    });

    public PaymentResultViewModel(@NonNull Application application) {
        super(application);
    }

}
