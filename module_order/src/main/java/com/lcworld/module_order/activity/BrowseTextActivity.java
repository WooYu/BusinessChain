package com.lcworld.module_order.activity;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityBrowseTextBinding;
import com.lcworld.module_order.viewmodel.BrowseTextViewModel;

/**
 * 只加载文本的activity
 * 0拼团客服电话 10关于我们 20拼团合同 30拼团支付协议 40 常见问题 50注册协议 60成长值介绍
 */
public class BrowseTextActivity extends BaseActivityEnhance<OrderActivityBrowseTextBinding, BrowseTextViewModel> {

    public final static String PARAM_SYSTXT = "param_systxt";
    private int mType;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_browse_text;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();

        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            return;
        }

        mType = bundle.getInt(PARAM_SYSTXT);

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initObsevableContent();
        initViewTitle(mType);
        viewModel.requestText(mType);
    }

    private void initViewTitle(int type) {
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int[] sysTxtArr = getResources().getIntArray(R.array.config_systxt);
        //0拼团客服电话 10关于我们 20拼团合同 30拼团支付协议 40 常见问题 50注册协议 60成长值介绍
        String title = "详情";
        if (type == sysTxtArr[1]) {
            title = getString(R.string.title_aboutus);
        } else if (type == sysTxtArr[2]) {
            title = getString(R.string.title_spellgroupcontract);
        } else if (type == sysTxtArr[3]) {
            title = getString(R.string.title_payAgreement);
        } else if (type == sysTxtArr[4]) {
            title = getString(R.string.title_faq);
        } else if (type == sysTxtArr[5]) {
            title = getString(R.string.title_registProtocol);
        } else if (type == sysTxtArr[6]) {
            title = getString(R.string.title_upgradeIntro);
        }

        binding.qmuiTopbar.setTitle(title);
    }

    private void initObsevableContent() {
        viewModel.valueOfText.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.tvContent.setText(viewModel.valueOfText.get());
            }
        });
    }

}
