package com.lcworld.module_home.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.module_home.fragment.HomeMsgListFrag;

import java.util.List;

/**
 * 消息ViewPage的Adapter
 */
public class HomeMsgVpAdapter extends FragmentPagerAdapter {
    private List<HomeMsgListFrag> fragments;

    public HomeMsgVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeMsgVpAdapter(FragmentManager fm, List<HomeMsgListFrag> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return ObjectUtils.isEmpty(fragments) ? 0 : fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

}
