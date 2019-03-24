package com.lcworld.module_order.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.*;
import android.widget.ImageView;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataRegions;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择
 */
public class AddressSelectFrag extends DialogFragment {
    private ImageView mIvCancel;
    private RecyclerView mRvTop;
    private RecyclerView mRvBottom;

    private BaseQuickAdapter<DataRegions, BaseViewHolder> mTopAdapter;
    private BaseQuickAdapter<DataRegions, BaseViewHolder> mBottomAdapter;

    private SparseArray<List<DataRegions>> mBottomSparseArray;
    private int mTopCheckedIndex = 0;
    private DataRegions mLastTopBean;

    private DialogDismissListener mListener;

    public interface DialogDismissListener {
        void callback(List<DataRegions> list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.order_fragment_address_select, container, false);
        mIvCancel = view.findViewById(R.id.iv_cancel);
        mRvTop = view.findViewById(R.id.rv_top);
        mRvBottom = view.findViewById(R.id.rv_bottom);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置dialog宽度
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    private void initView() {
        //取消按钮
        mIvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //顶部初始化
        mTopAdapter = new BaseQuickAdapter<DataRegions, BaseViewHolder>(R.layout.order_item_address_select_top) {
            @Override
            protected void convert(BaseViewHolder helper, DataRegions item) {
                helper.setText(R.id.tv_content, ObjectUtils.isNotEmpty(
                        item.getLocal_name()) ? item.getLocal_name() : getString(R.string.order_receiveraddr_choose));
                helper.setVisible(R.id.line, item.isBeChosen());
            }
        };
        mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                clickedTopItem(position);
            }
        });
        mRvTop.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvTop.setAdapter(mTopAdapter);

        //底部初始化
        mBottomAdapter = new BaseQuickAdapter<DataRegions, BaseViewHolder>(R.layout.order_item_address_select_bottom) {
            @Override
            protected void convert(BaseViewHolder helper, DataRegions item) {
                helper.setText(R.id.tv_content, item.getLocal_name());
                helper.setVisible(R.id.iv_flag, item.isBeChosen());
            }
        };
        mBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                clickedBottomItem(position);
            }
        });
        mRvBottom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvBottom.setAdapter(mBottomAdapter);

        mLastTopBean = new DataRegions();
        requestMemeberAddresssRegion(null);
    }

    public void setDismissListener(DialogDismissListener mListener) {
        this.mListener = mListener;
    }

    private void clickedTopItem(int position) {
        if (mTopAdapter.getData().get(position).isBeChosen()) {
            return;
        }

        mTopCheckedIndex = position;

        for (DataRegions bean : mTopAdapter.getData()) {
            bean.setBeChosen(false);
        }
        mTopAdapter.getData().get(position).setBeChosen(true);
        mTopAdapter.notifyDataSetChanged();

        mBottomAdapter.setNewData(mBottomSparseArray.get(mTopCheckedIndex));
    }

    private void clickedBottomItem(int position) {
        if (mBottomAdapter.getData().get(position).isBeChosen()) {
            return;
        }

        for (DataRegions bean : mBottomAdapter.getData()) {
            bean.setBeChosen(false);
        }
        DataRegions bottomBean = mBottomAdapter.getData().get(position);
        bottomBean.setBeChosen(true);
        mBottomAdapter.notifyDataSetChanged();

        int id = mBottomAdapter.getData().get(position).getId();
        if (mTopCheckedIndex >= (mTopAdapter.getData().size() - 1)) {
            mTopAdapter.getData().remove(mTopAdapter.getData().size() - 1);
            addData2TopList(bottomBean, mTopCheckedIndex);
            mTopAdapter.notifyDataSetChanged();
            requestMemeberAddresssRegion(bottomBean);
            return;
        }

        DataRegions nextTopBean = mTopAdapter.getData().get(mTopCheckedIndex + 1);
        if (id != nextTopBean.getParent_id()) {
            List<DataRegions> removeEndList = new ArrayList<>();
            for (int i = mTopCheckedIndex; i < mTopAdapter.getData().size(); i++) {
                removeEndList.add(mTopAdapter.getData().get(i));
            }
            mTopAdapter.getData().removeAll(removeEndList);
            addData2TopList(bottomBean, mTopCheckedIndex);
            mTopAdapter.notifyDataSetChanged();
            requestMemeberAddresssRegion(bottomBean);
        }

    }

    private void addData2TopList(DataRegions bottomBean, int addIndex) {
        DataRegions topbean = new DataRegions();
        topbean.setId(bottomBean.getId());
        topbean.setCod(bottomBean.getCod());
        topbean.setLocal_name(bottomBean.getLocal_name());
        topbean.setParent_id(bottomBean.getParent_id());
        topbean.setRegion_grade(bottomBean.getRegion_grade());
        topbean.setRegion_path(bottomBean.getRegion_path());
        topbean.setZipcode(bottomBean.getZipcode());
        mTopAdapter.getData().add(addIndex, topbean);
    }

    private void returnData() {
        if (null != mListener && ObjectUtils.isNotEmpty(mTopAdapter.getData())) {
            mListener.callback(mTopAdapter.getData());
        }
        dismiss();
    }

    public void requestMemeberAddresssRegion(final DataRegions parentRegions) {
//        if (mTopAdapter.getData().size() > 3) {
//            returnData();
//            return;
//        }

        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .regionsIdChildren(null == parentRegions ? 0 : parentRegions.getId())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataRegions>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataRegions>> listRequestResult) {
                        if (ObjectUtils.isEmpty(listRequestResult.getData())) {
                            returnData();
                            return;
                        }
                        //更新顶部数据
                        if (!mTopAdapter.getData().contains(mLastTopBean)) {
                            if (null != parentRegions) {
                                mLastTopBean.setParent_id(parentRegions.getId());
                            }
                            mTopAdapter.getData().add(mLastTopBean);
                        }
                        for (DataRegions topbean : mTopAdapter.getData()) {
                            topbean.setBeChosen(false);
                        }
                        mTopCheckedIndex = mTopAdapter.getData().size() - 1;
                        mTopAdapter.getData().get(mTopCheckedIndex).setBeChosen(true);
                        mTopAdapter.notifyDataSetChanged();

                        //更新底部数据
                        if (ObjectUtils.isEmpty(mBottomSparseArray)) {
                            mBottomSparseArray = new SparseArray<>();
                        }
                        mBottomSparseArray.put(mTopAdapter.getData().size() - 1, listRequestResult.getData());
                        mBottomAdapter.setNewData(listRequestResult.getData());

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        returnData();
                    }
                });
    }
}
