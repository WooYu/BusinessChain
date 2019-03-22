package com.lcworld.module_goods.fragment;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.utils.ConvertExUtils;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.bean.DataSKUVo;
import com.lcworld.module_goods.bean.EventReturnProductInfo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.KLog;

import java.util.ArrayList;

/**
 * 属性弹窗
 */
public class GoodsPropertyFrag extends DialogFragment implements View.OnClickListener, TextWatcher {

    private TextView mTvOperateSub;
    private TextView mTvInventory;
    private TextView mTvOperateAdd;
    private RecyclerView mRvProduct;
    private TextView mTvPrice;
    private EditText mEtQuantityValue;
    private QMUIRadiusImageView mIvPhoto;
    private Button mBtnSure;
    private View mBgAdd;
    private View mBgSub;
    private ImageView mIvPriceFlag;

    private ArrayList<DataSKUVo> skuVoArrayList;
    private BaseQuickAdapter<DataSKUVo, BaseViewHolder> mProductAdapter;
    private ObservableInt valueQuantityOfGoods = new ObservableInt();//购买数量
    private int valueMaxLimitQuantityOfGoods;//最大购买数量
    private int valueSkuId;//产品id

    public static GoodsPropertyFrag getIntance(Bundle bundle) {
        GoodsPropertyFrag propertyFrag = new GoodsPropertyFrag();
        propertyFrag.setArguments(bundle);
        return propertyFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.goods_frag_property_dialog, container, false);
        mIvPriceFlag = view.findViewById(R.id.iv_price_flag);
        mTvOperateSub = view.findViewById(R.id.tv_operate_sub);
        mTvInventory = view.findViewById(R.id.tv_inventory);
        mTvOperateAdd = view.findViewById(R.id.tv_operate_add);
        mRvProduct = view.findViewById(R.id.rv_product);
        mTvPrice = view.findViewById(R.id.tv_price);
        mEtQuantityValue = view.findViewById(R.id.et_quantity_value);
        mIvPhoto = view.findViewById(R.id.iv_photo);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mBgAdd = view.findViewById(R.id.bg_add);
        mBgSub = view.findViewById(R.id.bg_sub);

        mTvOperateAdd.setOnClickListener(this);
        mTvOperateSub.setOnClickListener(this);
        mEtQuantityValue.addTextChangedListener(this);
        mBtnSure.setOnClickListener(this);

        initObservable_QuantityChange();
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_operate_sub) {
            if (valueQuantityOfGoods.get() > 1) {
                mEtQuantityValue.setText(String.valueOf(valueQuantityOfGoods.get() - 1));
            }
        } else if (v.getId() == R.id.tv_operate_add) {
            if (valueQuantityOfGoods.get() < valueMaxLimitQuantityOfGoods) {
                mEtQuantityValue.setText(String.valueOf(valueQuantityOfGoods.get() + 1));
            }
        } else if (v.getId() == R.id.btn_sure) {
            KLog.d("valueQuantityOfGoods=" + valueQuantityOfGoods.get() + ", valueSkuId = " + valueSkuId);
            RxBus.getDefault().post(new EventReturnProductInfo(valueQuantityOfGoods.get(),valueSkuId));
            dismiss();
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String valueStr = mEtQuantityValue.getText().toString().trim();
        if (ObjectUtils.isEmpty(valueStr)) {
            mEtQuantityValue.setText(String.valueOf(1));
            return;
        }

        int quantity = Integer.parseInt(mEtQuantityValue.getText().toString().trim());
        if (quantity == valueQuantityOfGoods.get()) {
            return;
        }

        if (quantity == 0) {
            mEtQuantityValue.setText(String.valueOf(1));
        }

        if (quantity > valueMaxLimitQuantityOfGoods) {
            mEtQuantityValue.setText(String.valueOf(valueMaxLimitQuantityOfGoods));
        }

        valueQuantityOfGoods.set(Integer.parseInt(mEtQuantityValue.getText().toString().trim()));

    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            skuVoArrayList = bundle.getParcelableArrayList("skulist");
            initView();
        }
    }

    private void initView() {
        if (null == skuVoArrayList || skuVoArrayList.isEmpty()) {
            return;
        }
        DataSKUVo skuVo = skuVoArrayList.get(0);
        skuVo.setChecked(1);
        updateView_ProductInfo(skuVo);
        //产品分类
        mProductAdapter = new BaseQuickAdapter<DataSKUVo, BaseViewHolder>(R.layout.goods_item_product_category, skuVoArrayList) {
            @Override
            protected void convert(BaseViewHolder helper, DataSKUVo item) {
                helper.setText(R.id.tv_content, item.getName());
                TextView tvContent = helper.getView(R.id.tv_content);
                tvContent.setText(item.getName());
                boolean isChecked = item.getChecked() == 1;
                tvContent.setTextColor(getResources().getColor(isChecked ? R.color.tx_colorf : R.color.tx_colorb));
                tvContent.setBackgroundDrawable(getResources().getDrawable(isChecked ? R.drawable.shape_btnbg_g : R.drawable.shape_btnbg_o));
                if (isChecked) {
                    updateView_ProductInfo(item);
                }
            }
        };
        mRvProduct.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvProduct.setAdapter(mProductAdapter);
        mProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (DataSKUVo bean : mProductAdapter.getData()) {
                    bean.setChecked(0);
                }
                mProductAdapter.getData().get(position).setChecked(1);
                mProductAdapter.notifyDataSetChanged();
            }
        });

        //购买数量
        mEtQuantityValue.setText(String.valueOf(1));
    }

    private void initObservable_QuantityChange() {
        valueQuantityOfGoods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mTvOperateSub.setEnabled(valueQuantityOfGoods.get() != 1);
                mTvOperateAdd.setEnabled(valueQuantityOfGoods.get() != valueMaxLimitQuantityOfGoods);
            }
        });
    }

    private void updateView_ProductInfo(DataSKUVo skuVo) {
        //产品id
        valueSkuId = skuVo.getSku_id();
        //商品图片
        GlideApp.with(this).load(skuVo.getGoods_image()).into(mIvPhoto);
        //商品价格
        if (ObjectUtils.isNotEmpty(skuVo.getGoods_type()) && skuVo.getGoods_type().equals(getResources().getStringArray(R.array.goods_type)[2])) {
            mIvPriceFlag.setImageResource(R.mipmap.goods_detail_diamond_a);
        } else {
            mIvPriceFlag.setImageResource(R.mipmap.goods_detail_money_a);
        }
        mTvPrice.setText(ConvertExUtils.formatMoney(skuVo.getOriginal_price()));
        //商品库存
        valueMaxLimitQuantityOfGoods = skuVo.getEnable_quantity();
        mTvInventory.setText(String.format(getString(R.string.goods_format_product_inventory), valueMaxLimitQuantityOfGoods));
    }


}
