package com.lcworld.module_home.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_home.BR;
import com.lcworld.module_home.R;
import com.lcworld.module_home.databinding.HomeActivityApplyproviderBinding;
import com.lcworld.module_home.viewmodel.OperatorApplyViewModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

import java.io.File;
import java.util.List;

/**
 * 运营商申请
 */
public class OperatorApplyAct extends BaseActivityEnhance<HomeActivityApplyproviderBinding, OperatorApplyViewModel> {
    private final int RequestCode_SelectLicense = 0x0001;
    private final int RequestCode_SelectLogo = 0x0002;

    private int mPhotoAlbum_RequestCode;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_applyprovider;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initViewTitle();
        initViewClickListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (ObjectUtils.isEmpty(selectList)) {
                return;
            }

            File file = new File(selectList.get(0).getPath());
            if (mPhotoAlbum_RequestCode == RequestCode_SelectLicense) {
                viewModel.valueLicenseLocalPath.set(selectList.get(0).getPath());
                GlideApp.with(OperatorApplyAct.this)
                        .load(file)
                        .into(binding.ivLicense);
            } else {
                viewModel.valueLogoLocalPath.set(selectList.get(0).getPath());
                GlideApp.with(OperatorApplyAct.this)
                        .load(file)
                        .into(binding.ivLogo);
            }


//            viewModel.requetUploadPicture(mPhotoAlbum_RequestCode == RequestCode_SelectLicense);
        }

    }

    private void initViewTitle() {
        binding.qmuiTopbar.setTitle(R.string.home_provider_title);
        binding.qmuiTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewClickListener() {
        binding.ivLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoAlbum_RequestCode = RequestCode_SelectLicense;
                requestSelectImagePermission();
            }
        });
        binding.ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoAlbum_RequestCode = RequestCode_SelectLogo;
                requestSelectImagePermission();
            }
        });
    }

    private void requestSelectImagePermission() {
        //请求打开相机权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA
                , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            openPhotoAlbum();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            ToastUtils.showLong(R.string.permission_photoalbum_denied);
                        } else {
                            ToastUtils.showLong(R.string.permission_photoalbum_denied_neverask);
                        }
                    }
                });
    }

    private void openPhotoAlbum() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
}
