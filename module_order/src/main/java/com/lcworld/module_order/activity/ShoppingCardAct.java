package com.lcworld.module_order.activity;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.R;
import com.lcworld.module_order.adapter.TrolleyAdapter;
import com.lcworld.module_order.bean.DataSKUVo;
import com.lcworld.module_order.databinding.OrderActivityShoppingcartBinding;
import com.lcworld.module_order.viewmodel.TrolleyViewModel;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import me.goldze.mvvmhabit.BR;

/**
 * 购物车
 */
@Route(path = RouterActivityPath.Order.Pager_Trolley)
public class ShoppingCardAct extends BaseActivityEnhance<OrderActivityShoppingcartBinding, TrolleyViewModel> {

    private TrolleyAdapter mTrolleyAdapte;
    private Button btnTitleRight;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_shoppingcart;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewTitle();
        initViewRecyclerView();

        initObservableTrolleyList();
    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.order_trolley_title);
        btnTitleRight = binding.qmuiTopbar.addRightTextButton(viewModel.valueTitleRight.get(), QMUIViewHelper.generateViewId());
        btnTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.valueTrolleyEdit.set(!viewModel.valueTrolleyEdit.get());
                viewModel.valueTitleRight.set(getString(viewModel.valueTrolleyEdit.get()
                        ? R.string.order_trolley_subtitle2 : R.string.order_trolley_subtitle1));
                btnTitleRight.setText(viewModel.valueTitleRight.get());

            }
        });
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewRecyclerView() {
        mTrolleyAdapte = new TrolleyAdapter(R.layout.order_item_shoppingcart, viewModel.valueTrolleyList);
        mTrolleyAdapte.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
                        .withInt("goods_id", viewModel.valueTrolleyList.get(position).getGoods_id())
                        .navigation();
            }
        });
        binding.rvGoods.setLayoutManager(new LinearLayoutManager(this));
        binding.rvGoods.setAdapter(mTrolleyAdapte);
        mTrolleyAdapte.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickedChildView(view.getId(), position);
            }
        });
    }

    private void initObservableTrolleyList() {
        viewModel.valueTrolleyList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mTrolleyAdapte.setNewData(viewModel.valueTrolleyList);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mTrolleyAdapte.setNewData(null);
            }
        });
    }

    private void clickedChildView(int viewId, int position) {
        DataSKUVo bean = mTrolleyAdapte.getData().get(position);
        if (viewId == R.id.tv_quantity_value) {

        } else if (viewId == R.id.bg_add) {
            viewModel.requestUpdateGoodsNum(bean.getSku_id(), bean.getNum() + 1);
        } else if (viewId == R.id.bg_sub) {
            if (bean.getNum() <= 1) {
                return;
            }
            viewModel.requestUpdateGoodsNum(bean.getSku_id(), bean.getNum() - 1);
        } else if (viewId == R.id.iv_pick) {
            int[] checkStatusArr = getResources().getIntArray(R.array.config_trolley_checkstatus);
            int newCheckStatus;
            if (bean.getChecked() == checkStatusArr[1]) {
                newCheckStatus = checkStatusArr[0];
            } else {
                newCheckStatus = checkStatusArr[1];
            }
            viewModel.requestUpdateGoodsChecked(bean.getSku_id(), newCheckStatus);
        }
    }

}
