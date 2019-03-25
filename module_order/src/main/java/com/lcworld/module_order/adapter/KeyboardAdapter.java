package com.lcworld.module_order.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataKeyboard;

import java.util.List;

public class KeyboardAdapter extends BaseMultiItemQuickAdapter<DataKeyboard, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public KeyboardAdapter(List<DataKeyboard> data) {
        super(data);
        addItemType(DataKeyboard.Type_Figure, R.layout.order_item_paypsw_figure);
        addItemType(DataKeyboard.Type_Empty, R.layout.order_item_paypsw_empty);
        addItemType(DataKeyboard.Type_Delete, R.layout.order_item_paypsw_delete);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataKeyboard item) {
        switch (helper.getItemViewType()) {
            case DataKeyboard.Type_Figure:
                helper.setText(R.id.tv_figure, item.getContent());
                break;
            case DataKeyboard.Type_Empty:
                break;
            case DataKeyboard.Type_Delete:
                break;
            default:
                break;
        }
    }
}
