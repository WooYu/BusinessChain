package com.lcworld.module_system.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.databinding.Observable
import android.databinding.ObservableField
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.text.Editable
import android.text.TextWatcher
import com.blankj.utilcode.util.ToastUtils
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base_kotlin.util.StorageUtils
import com.lcworld.module_system.R
import com.lcworld.module_system.databinding.SystemActivityFeedbackBinding
import com.lcworld.module_system.viewmodel.FeedBackViewModel
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import com.tbruyelle.rxpermissions2.RxPermissions
import me.goldze.mvvmhabit.BR
import java.io.File

/**
 * 意见反馈
 */
class FeedBackActivity : BaseActivityEnhance<SystemActivityFeedbackBinding, FeedBackViewModel>() {
    companion object {
        const val REQ_READ_IMAGE = 101 //读取相册RequestCode
        const val REQ_REPLACE_IMAGE = 102//更改照片的RequestCode
        const val REQ_CAMERA = 103//拍照RequestCode
        const val CLICK_POSITION_1 = 1
        const val CLICK_POSITION_2 = 2
        const val CLICK_POSITION_3 = 3

    }

    private lateinit var photoPath: String //当前处理照片路径
    private lateinit var filePath: String //当前处理照片路径
    private val rxPermissions: RxPermissions by lazy { RxPermissions(this) }
    private var clickPosition: Int = CLICK_POSITION_1
    override fun initContentView(bundle: Bundle?): Int = R.layout.system_activity_feedback

    override fun initVariableId(): Int = BR.viewModel

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.system_feedback)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.inputLengthTips.set(s.toString().length.toString() + "/300")
                viewModel.btnEnable.set(s.toString().isNotEmpty())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
        binding.ivPhoto1.setOnClickListener {
            clickPosition = CLICK_POSITION_1
            doOnclick(viewModel.photo1)
        }
        binding.ivPhoto2.setOnClickListener {
            clickPosition = CLICK_POSITION_2
            doOnclick(viewModel.photo2)
        }
        binding.ivPhoto3.setOnClickListener {
            clickPosition = CLICK_POSITION_3
            doOnclick(viewModel.photo3)
        }
    }

    private fun doOnclick(photo: ObservableField<String>) {
        val photo = photo.get() ?: ""
        if (photo.isEmpty()) {
            showCreateDialog()
        } else {
            showUpdateDialog()
        }
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.photo1.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.showAdd1.set((viewModel.photo1.get() ?: "").isEmpty())
            }

        })
        viewModel.photo2.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.showAdd2.set((viewModel.photo2.get() ?: "").isEmpty())
            }

        })
        viewModel.photo3.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.showAdd3.set((viewModel.photo3.get() ?: "").isEmpty())
            }

        })
    }

    private fun showCreateDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(this)
            .addItem("相册")
            .addItem("拍照")
            .addItem("取消")
            .setOnSheetItemClickListener { dialog, itemView, position, tag ->
                dialog.dismiss()
                when (position) {
                    0 -> operateAlbumByType(REQ_READ_IMAGE)
                    1 -> takePhotoByCamera()
                    else -> {
                    }
                }
            }
            .build()
            .show()
    }

    /**
     * 操作相册获取照片
     * REQ_READ_IMAGE ->添加图片 读取相册
     * REQ_REPLACE_IMAGE ->更改图片 读取相册
     */
    @SuppressLint("CheckResult")
    private fun operateAlbumByType(type: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({ granted ->
                    run {
                        if (granted) {
                            selectPhotoByAlbum(type)
                        } else {
                            ToastUtils.showShort("已禁止文件读写权限，您可以在系统设置中打开")
                        }
                    }
                }, { ToastUtils.showShort(it.toString()) })
        } else {
            selectPhotoByAlbum(type)
        }
    }

    /**
     * 打开相册选取照片
     */
    private fun selectPhotoByAlbum(type: Int) {
        val intent: Intent = if (Build.VERSION.SDK_INT < 19) {
            intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            intent
        } else {
            intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent
        }
        startActivityForResult(
            Intent.createChooser(intent, "选择图片"), type
        )
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
        val directory = StorageUtils.getIndividualFileDirectory(this, "photos")
        if (!directory.exists()) {
            ToastUtils.showShort("无法读写文件")
            return
        }
        val imgFile = File(directory, System.currentTimeMillis().toString() + ".png")
        val uri: Uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri.fromFile(imgFile)
        } else {
            FileProvider.getUriForFile(applicationContext, this.packageName + ".provider", imgFile)
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, REQ_CAMERA)
        photoPath = uri.toString()
        filePath = imgFile.path
    }

    private fun showUpdateDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(this)
            .addItem("更改")
            .addItem("删除")
            .addItem("取消")
            .setOnSheetItemClickListener { dialog, itemView, position, tag ->
                dialog.dismiss()
                when (position) {
                    0 -> operateAlbumByType(REQ_REPLACE_IMAGE)
                    1 -> setImage("")
                    else -> {
                    }
                }
            }
            .build()
            .show()
    }

    private fun setImage(value: String) {
        when (clickPosition) {
            CLICK_POSITION_1 -> {
                viewModel.photo1.set(value)
            }
            CLICK_POSITION_2 -> {
                viewModel.photo2.set(value)
            }
            CLICK_POSITION_3 -> {
                viewModel.photo3.set(value)
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            REQ_CAMERA -> {
                setImage(filePath)
            }
            REQ_READ_IMAGE -> {
                dealAlbum(data)
                setImage(photoPath)
            }
            REQ_REPLACE_IMAGE -> {
                dealAlbum(data)
                setImage(photoPath)
            }
        }
    }

    /**
     * 处理相册 获取图片路径
     */
    private fun dealAlbum(data: Intent?) {
        data?.let {
            val originalUri = data.data ?: return
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(
                originalUri, projection, null, null, null
            )
            if (cursor != null) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                photoPath = "file://" + cursor.getString(columnIndex)
                if (cursor.isClosed) {

                } else cursor.close()
            } else {
                photoPath = originalUri.toString()
            }

        }
    }
}
