package com.lcworld.module_system.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivitySettingBinding
import com.lcworld.module_system.fragment.CompanyInfoFrag
import com.lcworld.module_system.fragment.PersonInfoFrag
import me.goldze.mvvmhabit.BR

/**
 * 设置
 */
@Route(path = RouterActivityPath.System.PAGER_SYSTEM)
class SettingActivity : BaseActivityEnhance<SystemActivitySettingBinding, BaseViewModelEnhance>() {
    private val tags = listOf("person", "company")
    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_setting

    override fun initVariableId(): Int = BR.viewModel
    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = "设置"
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        initTabLayout()
        switchFragment(0)
    }

    private fun initTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                switchFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun switchFragment(position: Int) {
        val fragment = supportFragmentManager.findFragmentByTag(tags[position])
        val transaction = supportFragmentManager.beginTransaction()
        tags.map {
            val f = supportFragmentManager.findFragmentByTag(it)
            if (f != null && f.isVisible) transaction.hide(f)
        }
        if (fragment != null) {
            transaction.show(fragment)
        } else {
            val fragment =
                if (position == 0) PersonInfoFrag.newInstance() else CompanyInfoFrag.newInstance()
            transaction.add(R.id.fl_info, fragment, tags[position])
        }
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
