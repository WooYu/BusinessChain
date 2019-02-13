package com.lcworld.module_goods.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.bean.GoodsClassifyLevelBBean;
import com.lcworld.module_goods.bean.GoodsClassifyLevelBSection;

import java.util.List;

public class GoodsClassifyLevelBAdapter extends BaseSectionQuickAdapter<GoodsClassifyLevelBSection, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public GoodsClassifyLevelBAdapter(int layoutResId, int sectionHeadResId, List<GoodsClassifyLevelBSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GoodsClassifyLevelBSection item) {
        helper.setText(R.id.tv_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsClassifyLevelBSection item) {
        GoodsClassifyLevelBBean bean = item.t;
        helper.setText(R.id.tv_name, bean.getName());
        GlideApp.with(mContext)
                .load(bean.getImgurl())
                .into((ImageView) helper.getView(R.id.iv_photo));
    }
}
