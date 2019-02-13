package com.lcworld.module_account.activity;

import android.os.Bundle;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_account.BR;
import com.lcworld.module_account.R;
import com.lcworld.module_account.bean.EventSetPassword;
import com.lcworld.module_account.databinding.AccountActivityRegistFirstBinding;
import com.lcworld.module_account.viewmodel.RegistViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * 注册第一步
 */
public class RegistFirstActivity extends BaseActivityEnhance<AccountActivityRegistFirstBinding, RegistViewModel> {

    private Disposable setPswDisposable;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.account_activity_regist_first;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initToolBar();
        initSetPasswordListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != setPswDisposable && !setPswDisposable.isDisposed()) {
            setPswDisposable.dispose();
        }
    }

    private void initToolBar() {
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.qmuiTopbar.setBackgroundDividerEnabled(false);

    }

    private void initSetPasswordListener() {
        setPswDisposable = RxBus.getDefault().toObservable(EventSetPassword.class)
                .subscribe(new Consumer<EventSetPassword>() {
                    @Override
                    public void accept(EventSetPassword eventSetPassword) throws Exception {
                        finish();
                    }
                });
    }


}
