package com.lcworld.module_home.activity;

import android.os.Bundle;
import android.widget.RadioGroup;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_home.BR;
import com.lcworld.module_home.R;
import com.lcworld.module_home.adapter.HomeMsgVpAdapter;
import com.lcworld.module_home.databinding.HomeActivityMsglistBinding;
import com.lcworld.module_home.fragment.HomeMsgListFrag;
import com.lcworld.module_home.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息列表
 */
public class MessageListAct extends BaseActivityEnhance<HomeActivityMsglistBinding, MessageViewModel> {
    private HomeMsgVpAdapter mMsgAdapter;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_msglist;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewViewPager();
        initViewRadioGroup();
    }

    private void initViewRadioGroup() {
        binding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_income) {
                    binding.contentViewPager.setCurrentItem(0);
                } else if (checkedId == R.id.rb_other) {
                    binding.contentViewPager.setCurrentItem(1);
                }
            }
        });
    }

    private void initViewViewPager() {
        List<HomeMsgListFrag> fraglist = new ArrayList<>();
        int[] msgTypeArr = getResources().getIntArray(R.array.config_msgtype);
        fraglist.add(HomeMsgListFrag.getInstance(msgTypeArr[0]));
        fraglist.add(HomeMsgListFrag.getInstance(msgTypeArr[1]));
        mMsgAdapter = new HomeMsgVpAdapter(getSupportFragmentManager(), fraglist);
        binding.contentViewPager.setAdapter(mMsgAdapter);
    }
}
