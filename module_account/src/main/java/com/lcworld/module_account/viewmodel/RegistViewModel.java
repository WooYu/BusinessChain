package com.lcworld.module_account.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.base.BrowseActivity;
import com.lcworld.library_base.http.RequestResultImp;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_account.ApiServiceInterf;
import com.lcworld.module_account.R;
import com.lcworld.module_account.activity.SetPasswordActivity;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;

import java.util.concurrent.TimeUnit;

public class RegistViewModel extends BaseViewModelEnhance {
    //Disposable
    private Disposable authcodeCountDownDisposable;

    //手机号的绑定
    public ObservableField<String> telephone = new ObservableField<>("");

    //手机号输入内容的监听
    public TextViewBindingAdapter.AfterTextChanged telephoneAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                nextbtn_enable();

                if (ObjectUtils.isEmpty(s)) {
                    uc.authcodeEnableObservable.set(false);
                }else{
                    uc.authcodeEnableObservable.set(RegexUtils.isMobileSimple(s.toString()));
                }

            }
        };
    }

    //验证码
    public ObservableField<String> authcodeValue = new ObservableField<>("");
    //发送验证码的绑定
    public ObservableField<String> authcode = new ObservableField<>(getApplication().getString(R.string.account_login_authcode));
    //发送验证码的点击事件
    public BindingCommand authcodeClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestAuthcode();
        }
    });

    //验证码的输入监听
    public TextViewBindingAdapter.AfterTextChanged authcodeAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                nextbtn_enable();
            }
        };
    }

    //推荐码的绑定
    public ObservableField<String> referralcode = new ObservableField<>("");

    //推荐码的输入监听
    public TextViewBindingAdapter.AfterTextChanged referralcodeAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                nextbtn_enable();
            }
        };
    }

    //协议同意的监听
    private ObservableField<Boolean> protocol = new ObservableField<>(false);
    //协议同意的选择
    public BindingCommand<Boolean> protocolCheckCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            protocol.set(hasFocus);
            nextbtn_enable();
        }
    });
    //协议跳转的点击
    public BindingCommand protocolCommand = new BindingCommand<>(
            new BindingAction() {
                @Override
                public void call() {
                    Bundle bundle = new Bundle();
                    bundle.putString(BrowseActivity.PARAM_URL, "http://www.baidu.com");
                    bundle.putString(BrowseActivity.PARAM_TITLE, getApplication().getString(R.string.account_regist_protocol_title));
                    startActivity(BrowseActivity.class, bundle);
                }
            });

    //下一步点击
    public BindingCommand nextClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestCheckAuthcode();
        }
    });

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //验证码是否可以点击观察者
        public ObservableBoolean authcodeEnableObservable = new ObservableBoolean(false);
        //下一步是否可以点击
        public ObservableBoolean nextEnableObservable = new ObservableBoolean(false);
    }

    public RegistViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != authcodeCountDownDisposable && !authcodeCountDownDisposable.isDisposed()) {
            authcodeCountDownDisposable.dispose();
        }
    }

    //下一步按钮状态更新
    private void nextbtn_enable() {
        boolean validPhone = RegexUtils.isMobileSimple(telephone.get());
        boolean validAuthcode = ObjectUtils.isNotEmpty(authcodeValue.get());
        boolean validReferralcode = ObjectUtils.isNotEmpty(referralcode.get());
        boolean agreeProtocol = protocol.get();
        uc.nextEnableObservable.set(validPhone && validAuthcode && validReferralcode && agreeProtocol);
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
                        authcode.set(String.format(getApplication().getString(R.string.account_format_resend)
                                , (validTime - aLong)));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //倒计时完毕置为可点击状态
                        uc.authcodeEnableObservable.set(true);
                        authcode.set(getApplication().getString(R.string.account_login_authcode));
                    }
                })
                .subscribe();
    }

    //获取验证码
    private void requestAuthcode() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .registerSmscode(telephone.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        authcode_StartCountDown();
                    }

                });

    }

    //校验验证码
    private void requestCheckAuthcode() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .registerSmscodeCheck(telephone.get(), authcodeValue.get(),referralcode.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        Bundle bundle = new Bundle();
                        bundle.putString("mobile", telephone.get());
                        bundle.putString("referralcode", referralcode.get());
                        startActivity(SetPasswordActivity.class,bundle);
                    }
                });

    }

}
