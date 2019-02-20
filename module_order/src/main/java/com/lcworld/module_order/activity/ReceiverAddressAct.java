package com.lcworld.module_order.activity;

import android.databinding.ObservableList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.adapter.ReceiverAddressAdapter;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.databinding.OrderActivityReceiveraddrBinding;
import com.lcworld.module_order.viewmodel.ReceiverAddressViewModel;

/**
 * 收货地址
 */
@Route(path = RouterActivityPath.Order.Pager_ReceiverAddress_List)
public class ReceiverAddressAct extends BaseActivityEnhance<OrderActivityReceiveraddrBinding, ReceiverAddressViewModel> {
    private ReceiverAddressAdapter mAddressAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_receiveraddr;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initView_Title();
        initView_RecyclerView();

        initObservableAddressList();
    }

    private void initView_Title() {
        binding.qmuiTopbar.setTitle(R.string.order_receiveraddr_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView_RecyclerView() {
        mAddressAdapter = new ReceiverAddressAdapter(R.layout.order_item_receiveraddr, null);
        binding.rvAddress.setLayoutManager(new LinearLayoutManager(this));
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(getResources().getDimension(R.dimen.tx_size20));
        paint.setColor(Color.WHITE);
        paint.getFontMetrics();
        final Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        final float heightOfText = fontMetrics.bottom - fontMetrics.top;
        final float widthOfText = paint.measureText("删除");
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
//                Log.d(TAG, "View onItemSwipeMoving:dX = " + dX + ",dY = " + dY + ",isCurrentlyActive = " + isCurrentlyActive);
                canvas.drawColor(getResources().getColor(R.color.tx_colore));

                View itemView = viewHolder.itemView;
                float yOfbaseline = (itemView.getHeight() + heightOfText) / 2 - fontMetrics.bottom;
                float xOfStart = (Math.abs(dX) - widthOfText) / 2;

                canvas.drawText("删除", xOfStart, yOfbaseline, paint);

            }
        };

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAddressAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(binding.rvAddress);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.END);
        mAddressAdapter.enableSwipeItem();
        mAddressAdapter.setOnItemSwipeListener(onItemSwipeListener);
        mAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickedItemChildClick(view, position);
            }
        });
        mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                viewModel.requestSetMemberAddress(position);
            }
        });
        binding.rvAddress.setAdapter(mAddressAdapter);
    }

    private void initObservableAddressList() {
        viewModel.observableList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeChanged(sender, positionStart, itemCount);
                mAddressAdapter.setNewData(viewModel.observableList);
            }
        });
    }

    private void clickedItemChildClick(View view, int position) {
        DataMemberAddress bean = mAddressAdapter.getData().get(position);
        if (view.getId() == R.id.view_defaul) {
            if (bean.getDef_addr() == getResources().getInteger(R.integer.config_def_address)) {
                return;
            }

            viewModel.requestMemberAddressModify(bean);
        } else if (view.getId() == R.id.tv_edit) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("memberAddress", bean);
            startActivity(ReceiverAddressEditAct.class, bundle);
        }
    }
}
