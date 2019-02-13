package com.lcworld.module_goods.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcworld.library_base.base.BaseFragmentEnhance;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.library_base.widget.textview.OnlyTextTab;
import com.lcworld.module_goods.BR;
import com.lcworld.module_goods.R;
import com.lcworld.module_goods.adapter.ClassifyAdapter;
import com.lcworld.module_goods.databinding.GoodsFragmentEntranceBinding;
import com.lcworld.module_goods.viewmodel.GoodsViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;

/**
 * Created by goldze on 2018/6/21
 */
@Deprecated
@Route(path = RouterFragmentPath.Goods.PAGER_ENTRANCE)
public class GoodsFragment extends BaseFragmentEnhance<GoodsFragmentEntranceBinding, GoodsViewModel> {

    @Override
    public int initContentView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return R.layout.goods_fragment_entrance;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        initTopBar();
        initClassifyNavi();
    }

    private void initTopBar() {
        binding.qmuiTopbar.setTitle(R.string.goods_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initClassifyNavi() {
        NavigationController navigationController = binding.naviLeft.custom()
                .addItem(new OnlyTextTab(getContext(), "A"))
                .addItem(new OnlyTextTab(getContext(), "B"))
                .addItem(new OnlyTextTab(getContext(), "C"))
                .addItem(new OnlyTextTab(getContext(), "D"))
                .addItem(new OnlyTextTab(getContext(), "E"))
                .addItem(new OnlyTextTab(getContext(), "F"))
                .addItem(new OnlyTextTab(getContext(), "G"))
                .addItem(new OnlyTextTab(getContext(), "H"))
                .addItem(new OnlyTextTab(getContext(), "I"))
                .addItem(new OnlyTextTab(getContext(), "J"))
                .addItem(new OnlyTextTab(getContext(), "K"))
                .addItem(new OnlyTextTab(getContext(), "L"))
                .addItem(new OnlyTextTab(getContext(), "M"))
                .addItem(new OnlyTextTab(getContext(), "N"))
                .addItem(new OnlyTextTab(getContext(), "O"))
                .enableVerticalLayout()
                .build();

        binding.viewPager.setAdapter(new ClassifyAdapter(getChildFragmentManager(), navigationController.getItemCount()));

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);
    }
}
