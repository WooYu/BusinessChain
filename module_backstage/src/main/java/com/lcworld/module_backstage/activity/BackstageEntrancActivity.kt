package com.lcworld.module_backstage.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_backstage.BR
import com.lcworld.module_backstage.R
import com.lcworld.module_backstage.databinding.BackAcitivityEntranceBinding

/**
 * 后台
 */
@Route(path = RouterActivityPath.Backstage.PAGER_ENTRANCE)
class BackstageEntrancActivity : BaseActivityEnhance<BackAcitivityEntranceBinding, BaseViewModelEnhance>() {
    override fun initContentView(bundle: Bundle): Int {
        return R.layout.back_acitivity_entrance
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
