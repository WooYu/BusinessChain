package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.module_order.bean.DataOrderDTO;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<DataOrderDTO, BaseViewHolder> {
    public OrderListAdapter(int layoutResId, @Nullable List<DataOrderDTO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataOrderDTO item) {

    }
}
