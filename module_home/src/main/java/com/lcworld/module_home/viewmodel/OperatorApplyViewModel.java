package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.activity.OperatorAuditResultAct;
import com.lcworld.module_home.bean.DataFileVo;
import com.lcworld.module_home.bean.DataGroupDTO;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

public class OperatorApplyViewModel extends BaseViewModelEnhance {

    public OperatorApplyViewModel(@NonNull Application application) {
        super(application);
    }

    //企业名称
    public final ObservableField<String> valueCompanyName = new ObservableField<>();
    //所属行业
    public final ObservableField<String> valueIndustry = new ObservableField<>();
    //联系人姓名
    public final ObservableField<String> valueLinkManName = new ObservableField<>();
    //联系人职位
    public final ObservableField<String> valueJobTitle = new ObservableField<>();
    //手机号码
    public final ObservableField<String> valuePhone = new ObservableField<>();
    //统一信用代码
    public final ObservableField<String> valueCreditNum = new ObservableField<>();
    //办公地址
    public final ObservableField<String> valueCompanyAddr = new ObservableField<>();
    //企业营业执照本地图片路径
    public final ObservableField<String> valueLicenseLocalPath = new ObservableField<>();
    //企业LOGO本地图片路径
    public final ObservableField<String> valueLogoLocalPath = new ObservableField<>();
    //提交审核是否可以点击
    public final ObservableBoolean enableBtnSubmit = new ObservableBoolean();
    //点击提交审核
    public final BindingCommand clickBtnSubmit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            clickedSubmitAudit();
        }
    });

    //edittext输入监听
    public TextViewBindingAdapter.AfterTextChanged textChangeInput() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                updateViewEnableBtnSubmit();
            }
        };
    }

    private void clickedSubmitAudit() {
        if (!RegexUtils.isMobileSimple(valuePhone.get())) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getResources().getString(R.string.home_error_phone));
            return;
        }

        requestApply();
    }

    private void updateViewEnableBtnSubmit() {
        enableBtnSubmit.set(ObjectUtils.isNotEmpty(valueCompanyName)
                && ObjectUtils.isNotEmpty(valueIndustry)
                && ObjectUtils.isNotEmpty(valueLinkManName)
                && ObjectUtils.isNotEmpty(valueJobTitle)
                && ObjectUtils.isNotEmpty(valuePhone)
                && ObjectUtils.isNotEmpty(valueCreditNum)
                && ObjectUtils.isNotEmpty(valueCompanyAddr)
                && ObjectUtils.isNotEmpty(valueLicenseLocalPath)
                && ObjectUtils.isNotEmpty(valueLogoLocalPath)
        );
    }

    public void requetUploadPicture(boolean bLicense) {
        File file = null;
        if (bLicense) {
            file = new File(valueLicenseLocalPath.get());
        } else {
            file = new File(valueLogoLocalPath.get());
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .fileUploaders(body, "other")
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataFileVo>>() {

                    @Override
                    public void onSuccess(RequestResult<DataFileVo> dataFileVoRequestResult) {

                    }
                });

    }

    public void requestApply() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .homeGroup(valueCompanyName.get(), valuePhone.get(), valuePhone.get(), valueLinkManName.get(), valueJobTitle.get()
                        , valueCreditNum.get(), valueCompanyAddr.get(), "", "")
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataGroupDTO>>() {

                    @Override
                    public void onSuccess(RequestResult<DataGroupDTO> dataGroupDTORequestResult) {
                        startActivity(OperatorAuditResultAct.class);
                        finish();
                    }
                });

    }
}
