package com.lcworld.module_home.activity;

import android.os.Bundle;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_home.BR;
import com.lcworld.module_home.R;
import com.lcworld.module_home.databinding.HomeActivityAuditresultBinding;
import com.lcworld.module_home.viewmodel.OperatorAuditResultViewModel;

/**
 * 审核结果
 */
public class OperatorAuditResultAct extends BaseActivityEnhance<HomeActivityAuditresultBinding, OperatorAuditResultViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_auditresult;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewTitle();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.qmuiTopbar.setTitle("审核结果");
    }
}
