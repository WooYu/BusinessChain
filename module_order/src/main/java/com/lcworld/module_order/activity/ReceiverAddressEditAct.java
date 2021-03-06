package com.lcworld.module_order.activity;

import android.os.Bundle;
import android.view.View;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.bean.DataRegions;
import com.lcworld.module_order.databinding.OrderActivityReceiveraddrEditBinding;
import com.lcworld.module_order.fragment.AddressSelectFrag;
import com.lcworld.module_order.viewmodel.ReceiverAddressEditViewModel;

import java.util.List;

/**
 * 收货地址:编辑、新建
 */
public class ReceiverAddressEditAct extends BaseActivityEnhance<OrderActivityReceiveraddrEditBinding
        , ReceiverAddressEditViewModel> {

    public DataMemberAddress mMemberAddress;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_receiveraddr_edit;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            mMemberAddress = bundle.getParcelable("memberAddress");
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewTitle();
        initViewListener();
        initViewEchoData();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(ObjectUtils.isEmpty(mMemberAddress) ? R.string.order_receiveraddr_create_title : R.string.order_receiveraddr_edit_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewListener() {
        binding.tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedChooseRegion();
            }
        });
    }

    private void initViewEchoData() {
        if (ObjectUtils.isEmpty(mMemberAddress)) {
            return;
        }

        viewModel.valuesModifyId.set(mMemberAddress.getAddr_id());
        viewModel.valueAddresseeName.set(mMemberAddress.getName());
        viewModel.valueAddressPhone.set(mMemberAddress.getMobile());
        viewModel.valueRegisons.set(mMemberAddress.getProvince() + mMemberAddress.getCity() + mMemberAddress.getCounty());
        viewModel.valueDetailAddress.set(mMemberAddress.getAddr());

        viewModel.valueProvince.set(mMemberAddress.getProvince());
        viewModel.valueProvinceId.set(mMemberAddress.getProvince_id());
        viewModel.valueCity.set(mMemberAddress.getCity());
        viewModel.valueCityId.set(mMemberAddress.getCity_id());
        viewModel.valueCounty.set(mMemberAddress.getCounty());
        viewModel.valueCountyId.set(mMemberAddress.getCounty_id());
        viewModel.valueDefAddr.set(mMemberAddress.getDef_addr());
    }

    private void clickedChooseRegion() {
        AddressSelectFrag addressSelectFrag = new AddressSelectFrag();
        addressSelectFrag.show(getSupportFragmentManager(), addressSelectFrag.getTag());
        addressSelectFrag.setDismissListener(new AddressSelectFrag.DialogDismissListener() {
            @Override
            public void callback(List<DataRegions> list) {
                dealChooseRegion(list);
            }
        });
    }

    private void dealChooseRegion(List<DataRegions> list) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            DataRegions bean = list.get(i);
            if (0 == i) {
                viewModel.valueProvince.set(bean.getLocal_name());
                viewModel.valueProvinceId.set(bean.getId());
            }

            if (1 == i) {
                viewModel.valueCity.set(bean.getLocal_name());
                viewModel.valueCityId.set(bean.getId());
            }

            if (2 == i) {
                viewModel.valueCounty.set(bean.getLocal_name());
                viewModel.valueCountyId.set(bean.getId());
            }

            if (ObjectUtils.isNotEmpty(bean.getLocal_name())) {
                buffer.append(bean.getLocal_name());
            }
        }

        viewModel.valueRegisons.set(buffer.toString());
    }

}
