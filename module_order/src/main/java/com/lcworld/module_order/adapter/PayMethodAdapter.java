package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataPaymentMethodVo;

import java.util.List;

/**
 * 支付方式Adapter
 */
public class PayMethodAdapter extends BaseQuickAdapter<DataPaymentMethodVo, BaseViewHolder> {
    private int mChoicePosition = -1;

    public PayMethodAdapter(int layoutResId, @Nullable List<DataPaymentMethodVo> data) {
        super(layoutResId, data);
    }

    public void setmChoicePosition(int mChoicePosition) {
        this.mChoicePosition = mChoicePosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, DataPaymentMethodVo item) {
        GlideApp.with(mContext)
                .load(item.getImage())
                .into((ImageView)helper.getView(R.id.iv_paymethod_icon));
        helper.setText(R.id.tv_paymethod_name,item.getMethod_name())
                .setChecked(R.id.cbox_paymethod,mChoicePosition == helper.getAdapterPosition());

        helper.addOnClickListener(R.id.cbox_paymethod);
    }
}
