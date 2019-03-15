package com.lcworld.module_home.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataMessageNoticeLog;

import java.util.List;

public class MessageListAdapter extends BaseQuickAdapter<DataMessageNoticeLog, BaseViewHolder> {
    public MessageListAdapter(int layoutResId, @Nullable List<DataMessageNoticeLog> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataMessageNoticeLog item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_time, ConvertExUtils.convertTime(item.getReceive_time()));
    }
}
