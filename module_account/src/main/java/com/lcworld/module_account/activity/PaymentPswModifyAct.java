package com.lcworld.module_account.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_account.BR;
import com.lcworld.module_account.R;
import com.lcworld.module_account.databinding.AccountActivityPaymentPswmodifyBinding;
import com.lcworld.module_account.viewmodel.PaymentPswModifyViewModel;

@Route(path = RouterActivityPath.Account.PAGER_Payment_Psw_Modify)
public class PaymentPswModifyAct extends BaseActivityEnhance<AccountActivityPaymentPswmodifyBinding, PaymentPswModifyViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.account_activity_payment_pswmodify;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewToolBar();
        initPswToogle();
    }

    private void initViewToolBar() {
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.qmuiTopbar.setTitle(R.string.account_payment_psw_title);
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
