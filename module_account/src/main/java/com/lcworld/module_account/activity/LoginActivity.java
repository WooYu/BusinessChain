package com.lcworld.module_account.activity;

import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_account.BR;
import com.lcworld.module_account.R;
import com.lcworld.module_account.databinding.AccountActivityLoginBinding;
import com.lcworld.module_account.viewmodel.LoginViewModel;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * 登录页面
 */
@Route(path = RouterActivityPath.Account.PAGER_LOGIN)
public class LoginActivity extends BaseActivityEnhance<AccountActivityLoginBinding, LoginViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.account_activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initToolBar();
        initObservable_SwitchLoginMode();
        updateView();
    }

    private void initToolBar() {
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.qmuiTopbar.addRightTextButton(R.string.account_login_regist, QMUITopBarLayout.generateViewId())
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, RegistFirstActivity.class));
                    }
                });
        binding.qmuiTopbar.setBackgroundDividerEnabled(false);
    }

    private void initObservable_SwitchLoginMode() {
        //默认是密码登录，true是验证码登录
        viewModel.uc.pSwitchObservable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateView();
            }
        });
    }

    private void updateView() {
        if (viewModel.uc.pSwitchObservable.get()) {
            binding.tvTitle.setText(R.string.account_login_title2);
            binding.tvSubtitle.setText(R.string.account_login_subtitle2);
            binding.etPhone.setHint(R.string.account_hint_phone2);
            binding.etCode.setHint(R.string.account_hint_psw2);
            binding.tvCountdown.setVisibility(View.VISIBLE);
            binding.cboxSwitchLoginmode.setText(R.string.account_login_pswlogin);
        } else {
            binding.tvTitle.setText(R.string.account_login_title1);
            binding.tvSubtitle.setText(R.string.account_login_subtitle1);
            binding.etPhone.setHint(R.string.account_hint_phone1);
            binding.etCode.setHint(R.string.account_hint_psw1);
            binding.tvCountdown.setVisibility(View.INVISIBLE);
            binding.cboxSwitchLoginmode.setText(R.string.account_login_textlogin);
        }
    }
}
