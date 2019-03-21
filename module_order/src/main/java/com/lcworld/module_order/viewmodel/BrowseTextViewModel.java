package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;

public class BrowseTextViewModel extends BaseViewModelEnhance {

    public BrowseTextViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> valueOfText = new ObservableField<>();

    public void requestText(int type) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .sysTxt(type)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        valueOfText.set(requestResultImp.getData());
                    }
                });
    }

}
