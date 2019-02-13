package com.lcworld.module_account.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_account.R;
import com.lcworld.module_account.databinding.AccountActivityRegistSecondBinding;
import com.lcworld.module_account.viewmodel.SetPasswordViewModel;
import me.goldze.mvvmhabit.BR;

/**
 * 设置密码
 */
public class SetPasswordActivity extends BaseActivityEnhance<AccountActivityRegistSecondBinding, SetPasswordViewModel> {

    private String mMobile;
    private String mReferralCode;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.account_activity_regist_second;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if(null != bundle){
            mMobile = bundle.getString("mobile");
            mReferralCode = bundle.getString("referralcode");

            viewModel.tellphone.set(mMobile);
            viewModel.referralcode.set(mReferralCode);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initToolBar();
        initPswToogle();
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

    private void initPswToogle() {
        viewModel.uc.pSwitchObservable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.uc.pSwitchObservable.get()) {
                    binding.etPswa.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.etPswb.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    binding.etPswa.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.etPswb.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
