package com.lcworld.module_home.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.module_home.bean.DataGoodsInfo;

import java.util.List;

public class SearchGoodsAdapter extends BaseQuickAdapter<DataGoodsInfo, BaseViewHolder> {
    public SearchGoodsAdapter(int layoutResId, @Nullable List<DataGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataGoodsInfo item) {

    }
}
