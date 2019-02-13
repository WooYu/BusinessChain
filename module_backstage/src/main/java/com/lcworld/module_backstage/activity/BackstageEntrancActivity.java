package com.lcworld.module_backstage.activity;

import android.os.Bundle;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.module_backstage.BR;
import com.lcworld.module_backstage.R;
import com.lcworld.module_backstage.databinding.BackAcitivityEntranceBinding;

public class BackstageEntrancActivity extends BaseActivityEnhance<BackAcitivityEntranceBinding, BaseViewModelEnhance> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.back_acitivity_entrance;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
