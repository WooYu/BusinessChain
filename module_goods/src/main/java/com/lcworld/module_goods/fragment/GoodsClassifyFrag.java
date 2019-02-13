package com.lcworld.module_goods.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.adapter.GoodsClassifyLevelBAdapter;
import com.lcworld.module_goods.bean.GoodsClassifyLevelBSection;
import com.lcworld.module_goods.databinding.GoodsFragClassifyBinding;
import com.lcworld.module_goods.viewmodel.GoodsClassifyModel;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 分类
 */
public class GoodsClassifyFrag extends BaseFragmentEnhance<GoodsFragClassifyBinding, GoodsClassifyModel> {
    private static final String ARG_C = "content";
    private GoodsClassifyLevelBAdapter mSectionAdapter;

    public static GoodsClassifyFrag newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        GoodsClassifyFrag fragment = new GoodsClassifyFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.goods_frag_classify;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mSectionAdapter = new GoodsClassifyLevelBAdapter(R.layout.goods_item_classifylevelb_content
                , R.layout.goods_item_classifylevelb_head, binding.getViewModel().observableList);

        mSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsClassifyLevelBSection mySection = mSectionAdapter.getData().get(position);
                if (!mySection.isHeader) {
                    ToastUtils.showShort("路由到商品分类");
                }
            }
        });
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerview.setAdapter(mSectionAdapter);
    }
}
