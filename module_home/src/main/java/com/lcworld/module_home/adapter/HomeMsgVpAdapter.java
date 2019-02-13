package com.lcworld.module_home.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.module_home.fragment.HomeMsgListFrag;

import java.util.List;

/**
 * 消息ViewPage的Adapter
 */
public class HomeMsgVpAdapter extends PagerAdapter {
    private List<Fragment> fragments;

    @Override
    public int getCount() {
        return ObjectUtils.isEmpty(fragments) ? 0 : fragments.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return new HomeMsgListFrag();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

}
