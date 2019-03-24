package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.bean.EventUpdateReceiverAddress;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;

public class ReceiverAddressEditViewModel extends BaseViewModelEnhance {
    //待修改地址的id
    public final ObservableInt valuesModifyId = new ObservableInt(-1);
    //收件人姓名
    public final ObservableField<String> valueAddresseeName = new ObservableField<>();
    //手机号
    public final ObservableField<String> valueAddressPhone = new ObservableField<>();
    //所在区域
    public final ObservableField<String> valueRegisons = new ObservableField<>();
    //详细地址
    public final ObservableField<String> valueDetailAddress = new ObservableField<>();
    //省的值
    public final ObservableField<String> valueProvince = new ObservableField<>();
    public final ObservableInt valueProvinceId = new ObservableInt();
    //市的值
    public final ObservableField<String> valueCity = new ObservableField<>();
    public final ObservableInt valueCityId = new ObservableInt();
    //县的值
    public final ObservableField<String> valueCounty = new ObservableField<>();
    public final ObservableInt valueCountyId = new ObservableInt();
    //是否是默认地址
    public final ObservableInt valueDefAddr = new ObservableInt();

    //是否可以保存
    public final ObservableBoolean enableBthSave = new ObservableBoolean(false);

    //保存按钮的点击事件
    public final BindingCommand clickOfBtnSave = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            clickedBtnSave();
        }
    });

    public ReceiverAddressEditViewModel(@NonNull Application application) {
        super(application);

        initObservableData();
    }

    private void initObservableData() {
        valueAddresseeName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (ObjectUtils.isNotEmpty(valueAddresseeName)) {
                    updateValue_EnableBtnSave();
                } else {
                    enableBthSave.set(false);
                }
            }
        });
        valueAddressPhone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (ObjectUtils.isNotEmpty(valueAddressPhone)) {
                    updateValue_EnableBtnSave();
                } else {
                    enableBthSave.set(false);
                }
            }
        });
        valueRegisons.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (ObjectUtils.isNotEmpty(valueRegisons)) {
                    updateValue_EnableBtnSave();
                } else {
                    enableBthSave.set(false);
                }
            }
        });
        valueDetailAddress.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (ObjectUtils.isNotEmpty(valueDetailAddress)) {
                    updateValue_EnableBtnSave();
                } else {
                    enableBthSave.set(false);
                }
            }
        });

    }

    private void updateValue_EnableBtnSave() {
        boolean legalName = ObjectUtils.isNotEmpty(valueAddresseeName.get());
        boolean legalPhone = RegexUtils.isMobileSimple(valueAddressPhone.get());
        boolean legalRegion = ObjectUtils.isNotEmpty(valueRegisons.get());
        boolean legalAddr = ObjectUtils.isNotEmpty(valueDetailAddress.get());

        enableBthSave.set(legalName && legalPhone && legalRegion && legalAddr);
    }

    private void clickedBtnSave() {
        if (!RegexUtils.isMobileSimple(valueAddressPhone.get())) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.order_error_phone));
            return;
        }

        if (valuesModifyId.get() == -1) {
            requestMemberAddressCreate();
        } else {
            requestMemberAddressModify();
        }
    }

    //请求添加地址
    public void requestMemberAddressCreate() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersAddressCreate(valueAddresseeName.get(), valueAddressPhone.get(), valueProvinceId.get()
                        , valueProvince.get(), valueCityId.get(), valueCity.get()
                        , valueCountyId.get(), valueCounty.get(), valueDetailAddress.get(), valueDefAddr.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataMemberAddress>>() {

                    @Override
                    public void onSuccess(RequestResult<DataMemberAddress> dataMemberAddressRequestResult) {
                        RxBus.getDefault().post(new EventUpdateReceiverAddress());
                        finish();
                    }
                });
    }

    //请求修改地址
    public void requestMemberAddressModify() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .memberAddressModify(valuesModifyId.get(), valueAddresseeName.get(), valueAddressPhone.get(), valueProvinceId.get()
                        , valueProvince.get(), valueCityId.get(), valueCity.get()
                        , valueCountyId.get(), valueCounty.get(), valueDetailAddress.get(), valueDefAddr.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataMemberAddress>>() {
                    @Override
                    public void onSuccess(RequestResult<DataMemberAddress> dataMemberAddressRequestResult) {
                        RxBus.getDefault().post(new EventUpdateReceiverAddress());
                        finish();
                    }
                });
    }

}
