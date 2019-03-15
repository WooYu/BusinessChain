package com.lcworld.module_system.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.databinding.Observable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseFragmentEnhance
import com.lcworld.library_base_kotlin.util.StorageUtils
import com.lcworld.module_system.BR
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemFragmentCompanyInfoBinding
import com.lcworld.module_system.viewmodel.CompanyInfoViewModel
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.File


/**
 * 订单列表
 */
class CompanyInfoFrag : BaseFragmentEnhance<SystemFragmentCompanyInfoBinding, CompanyInfoViewModel>() {

    companion object {
        fun newInstance(): CompanyInfoFrag {
            return CompanyInfoFrag()
        }

        const val INDEX_0 = 0 //企业LOGO
        const val INDEX_1 = 1 //企业名称
        const val INDEX_2 = 2 //联系人
        const val INDEX_3 = 3 //职务
        const val INDEX_4 = 4 //手机号
        const val INDEX_5 = 5 //统一信用代码
        const val INDEX_6 = 6 //办公地址
        const val INDEX_7 = 7 //营业执照
    }

    private lateinit var filePath: String //当前处理照片路径
    private lateinit var rxPermissions: RxPermissions

    override fun initContentView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, bundle: Bundle?): Int {
        return R.layout.system_fragment_company_info
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        rxPermissions = RxPermissions(requireActivity())
        binding.ivBusinessLicense.setOnClickListener {
            takePhotoByCamera()
        }
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.companyInfo.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.showAdd.set((viewModel.companyInfo.get() ?: "").isEmpty())
            }

        })

    }

    /**
     * 调用相机获取照片
     */
    @SuppressLint("CheckResult")
    private fun takePhotoByCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rxPermissions.request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                .subscribe({ granted ->
                    run {
                        if (granted) {
                            takeCamera()
                        } else {
                            ToastUtils.showShort("已禁止文件读写权限，您可以在系统设置中打开")
                        }
                    }
                },
                    { ToastUtils.showShort(it.toString()) })
        } else {
            takeCamera()
        }
    }

    /**
     * 拍照
     */
    private fun takeCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val directory = StorageUtils.getIndividualFileDirectory(requireActivity(), "photos")
        if (!directory.exists()) {
            ToastUtils.showShort("无法读写文件")
            return
        }
        val imgFile = File(directory, System.currentTimeMillis().toString() + ".png")
        val uri: Uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri.fromFile(imgFile)
        } else {
            FileProvider.getUriForFile(
                requireActivity().applicationContext,
                requireActivity().packageName + ".provider",
                imgFile
            )
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, PersonInfoFrag.REQ_CAMERA)
        filePath = imgFile.path
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == PersonInfoFrag.REQ_CAMERA) {
            viewModel.companyInfo.set(filePath)
        }
    }
}
