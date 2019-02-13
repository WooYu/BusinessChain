package com.lcworld.module_account.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.RegexExUtil;
import com.lcworld.library_base.http.*;
import com.lcworld.module_account.ApiServiceInterf;
import com.lcworld.module_account.R;
import com.lcworld.module_account.activity.RetrieveFirstActivity;
import com.lcworld.module_account.bean.DataLogin;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;

import java.util.concurrent.TimeUnit;

public class LoginViewModel extends BaseViewModelEnhance {
    //Disposable
    private Disposable authcodeCountDownDisposable;

    //手机号的绑定
    public ObservableField<String> telephone = new ObservableField<>("");
    //密码或者验证码的绑定
    public ObservableField<String> logincode = new ObservableField<>("");
    //发送验证码的绑定
    public ObservableField<String> countdown = new ObservableField<>(getApplication().getString(R.string.account_login_authcode));
    //登录方式切换
    public BindingCommand<Boolean> loginModeCheckCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean isChecked) {
            uc.pSwitchObservable.set(isChecked);
        }
    });

    //手机号输入监听
    public TextViewBindingAdapter.AfterTextChanged telephoneAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                surebtn_enable();

                if (ObjectUtils.isEmpty(s)) {
                    uc.authcodeEnableObservable.set(false);
                } else {
                    uc.authcodeEnableObservable.set(RegexUtils.isMobileSimple(s.toString()));
                }

            }
        };
    }

    //密码或者验证码输入监听
    public TextViewBindingAdapter.AfterTextChanged codeAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                surebtn_enable();
            }
        };
    }

    //发送验证码的点击事件
    public BindingCommand clickCommand_Countdonw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestAuthcode();
        }
    });
    //点击忘记密码
    public BindingCommand clickCommand_RetrievePsw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RetrieveFirstActivity.class);
        }
    });
    //点击登录按钮
    public BindingCommand clickCommand_LoginBtn = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestLogin();
        }
    });

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //登录模式切换观察者
        public ObservableBoolean pSwitchObservable = new ObservableBoolean(false);
        //登录按钮是否可以点击
        public ObservableBoolean nextEnableObservable = new ObservableBoolean(false);
        //验证码是否可以点击观察者
        public ObservableBoolean authcodeEnableObservable = new ObservableBoolean(false);
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != authcodeCountDownDisposable && !authcodeCountDownDisposable.isDisposed()) {
            authcodeCountDownDisposable.dispose();
        }
    }

    //登录按钮点击状态
    private void surebtn_enable() {
        boolean validTelephone = RegexUtils.isMobileSimple(telephone.get());
        boolean validCode;
        if (uc.pSwitchObservable.get()) {
            validCode = ObjectUtils.isNotEmpty(logincode.get());
        } else {
            validCode = RegexExUtil.isLoginPassword(logincode.get());
        }
        uc.nextEnableObservable.set(validTelephone && validCode);
    }

    //验证码倒计时
    private void authcode_StartCountDown() {
        final int validTime = getApplication().getResources().getInteger(R.integer.config_validtime_authcode);
        uc.authcodeEnableObservable.set(false);
        authcodeCountDownDisposable = Flowable.intervalRange(0, validTime + 1, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        countdown.set(String.format(getApplication().getString(R.string.account_format_resend)
                                , (validTime - aLong)));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //倒计时完毕置为可点击状态
                        uc.authcodeEnableObservable.set(true);
                        countdown.set(getApplication().getString(R.string.account_login_authcode));
                    }
                })
                .subscribe();
    }


    //获取验证码
    private void requestAuthcode() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .loginSmscode(telephone.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        authcode_StartCountDown();
                    }

                });

    }

    //登录
    private void requestLogin() {
        ApiServiceInterf apiServiceInterf = RetrofitClient.getInstance().create(ApiServiceInterf.class);
        Observable<RequestResult<DataLogin>> observable;
        if (uc.pSwitchObservable.get()) {
            observable = apiServiceInterf.loginModeB(telephone.get(), logincode.get());
        } else {
            observable = apiServiceInterf.loginModeA(telephone.get(), EncryptUtils.encryptMD5ToString(logincode.get()).toLowerCase());
        }
        observable
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataLogin>>() {
                    @Override
                    public void onSuccess(RequestResult<DataLogin> result) {
                        ToastUtils.showShort("登录成功");
                    }

                });

    }
}
