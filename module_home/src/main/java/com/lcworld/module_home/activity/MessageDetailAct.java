package com.lcworld.module_home.activity;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_home.BR;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataMessageNoticeLog;
import com.lcworld.module_home.databinding.HomeActivityMsgdetailBinding;
import com.lcworld.module_home.viewmodel.MsgDetailViewModel;

/**
 * 消息详情
 */
@Route(path = RouterActivityPath.Home.Pager_Message_Detail)
public class MessageDetailAct extends BaseActivityEnhance<HomeActivityMsgdetailBinding, MsgDetailViewModel> {
    @Autowired
    DataMessageNoticeLog messageNoticeLog;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_msgdetail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewTitle();
    }

    private void initViewTitle(){
        binding.qmuiTopbar.setTitle("详情");
    }
}
