package com.lcworld.module_goods.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.lcworld.module_goods.fragment.GoodsClassifyFrag;

public class ClassifyAdapter extends FragmentPagerAdapter {

    private int size;

    public ClassifyAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        return GoodsClassifyFrag.newInstance(position + "");
    }

    @Override
    public int getCount() {
        return size;
    }
}
