package com.lcworld.module_order.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataMemberAddress;

import java.util.List;

/**
 * 收货地址列表的Adapter
 */
public class ReceiverAddressAdapter extends BaseItemDraggableAdapter<DataMemberAddress, BaseViewHolder> {
    public ReceiverAddressAdapter(int layoutResId, List<DataMemberAddress> data) {
        super(layoutResId, data);
    }

    public ReceiverAddressAdapter(List<DataMemberAddress> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataMemberAddress item) {
        helper.setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_phone,item.getMobile())
                .setText(R.id.tv_address,item.getAddr())
                .setChecked(R.id.cbox_default,item.getDef_addr() == mContext.getResources().getInteger(R.integer.config_def_address));

        helper.addOnClickListener(R.id.view_defaul);
        helper.addOnClickListener(R.id.tv_edit);
    }
}
