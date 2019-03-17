package com.lcworld.module_account.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.extension.utils.RegexExUtil;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_account.ApiServiceInterf;
import com.lcworld.module_account.R;
import com.lcworld.module_account.bean.EventSetPassword;
import io.reactivex.Observable;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.RxBus;

public class SetPasswordViewModel extends BaseViewModelEnhance {
    //手机号监听
    public ObservableField<String> tellphone = new ObservableField<>("");
    //推荐码监听
    public ObservableField<String> referralcode = new ObservableField<>("");

    //登录密码的监听
    public ObservableField<String> loginpsw = new ObservableField<>("");
    //确认密码的监听
    public ObservableField<String> confirmpsw = new ObservableField<>("");

    //登录密码的输入监听
    public TextViewBindingAdapter.AfterTextChanged loginpswAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                surebtn_enable();
            }
        };
    }

    //确认密码的输入监听
    public TextViewBindingAdapter.AfterTextChanged confirmpswAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                surebtn_enable();
            }
        };
    }

    //密码显示和隐藏的监听
    public BindingCommand<Boolean> toogleCheckCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            uc.pSwitchObservable.set(hasFocus);
        }
    });

    //确认密码的监听
    public BindingCommand nextClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestSetPassword();
        }
    });


    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public ObservableBoolean pSwitchObservable = new ObservableBoolean(false);
        //确认是否可以点击
        public ObservableBoolean nextEnableObservable = new ObservableBoolean(false);
    }


    public SetPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    private void surebtn_enable() {
        boolean notEmptyLoginPsw = ObjectUtils.isNotEmpty(loginpsw.get());
        boolean notEmptyConfirmPsw = ObjectUtils.isNotEmpty(confirmpsw.get());
        uc.nextEnableObservable.set(notEmptyLoginPsw && notEmptyConfirmPsw);
    }

    //点击确认密码
    private void requestSetPassword() {
        boolean validLoginPsw = RegexExUtil.isLoginPassword(loginpsw.get());
        if (!validLoginPsw) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.account_error_psw));
            return;
        }

        boolean samePsw = loginpsw.get().equals(confirmpsw.get());
        if (!samePsw) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.account_error_pswmismatch));
            return;
        }


        ApiServiceInterf apiServiceInterf = RetrofitClient.getInstance().create(ApiServiceInterf.class);
        Observable<RequestResultImp> observable;
        if (ObjectUtils.isEmpty(referralcode.get())) {
            observable = apiServiceInterf.findpswUpdatePsw(tellphone.get(), EncryptUtils.encryptMD5ToString(loginpsw.get()).toLowerCase());
        } else {
            observable = apiServiceInterf.registerApp(referralcode.get(), tellphone.get(), EncryptUtils.encryptMD5ToString(loginpsw.get()).toLowerCase());
        }

        observable
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        RxBus.getDefault().post(new EventSetPassword());
                        finish();
                    }
                });
    }
}
