package com.lcworld.module_order.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.activity.ReceiverAddressEditAct;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.bean.EventSelectReceiverAddress;
import com.lcworld.module_order.bean.EventUpdateReceiverAddress;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;

public class ReceiverAddressItemViewModel extends ItemViewModel<ReceiverAddressViewModel> {
    public ObservableField<DataMemberAddress> entity = new ObservableField<>();

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestSetMemberAddress(entity.get().getAddr_id());
        }
    });
    //默认的点击事件
    public final BindingCommand clickOfDefAddr = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (null == entity.get()) {
                return;
            }

            if (entity.get().getDef_addr()
                    == viewModel.getApplication().getResources().getInteger(R.integer.config_def_address)) {
                return;
            }

            requestMemberAddressModify(entity.get());
        }
    });
    //编辑的点击事件
    public final BindingCommand clickOfEdit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("memberAddress", entity.get());
            viewModel.startActivity(ReceiverAddressEditAct.class, bundle);
        }
    });

    public ReceiverAddressItemViewModel(@NonNull ReceiverAddressViewModel viewModel, DataMemberAddress entity) {
        super(viewModel);
        this.entity.set(entity);
    }

    public ReceiverAddressItemViewModel(@NonNull ReceiverAddressViewModel viewModel) {
        super(viewModel);
    }

    //请求修改默认地址
    public void requestMemberAddressModify(DataMemberAddress bean) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .memberAddressDefault(bean.getAddr_id())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataMemberAddress>>() {
                    @Override
                    public void onSuccess(RequestResult<DataMemberAddress> dataMemberAddressRequestResult) {
                        RxBus.getDefault().post(new EventUpdateReceiverAddress());
                    }
                });
    }

    //请求设置收货地址
    public void requestSetMemberAddress(int address_id) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCheckoutParamsAddressId(address_id)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataMemberAddress>>() {
                    @Override
                    public void onSuccess(RequestResult<DataMemberAddress> dataMemberAddressRequestResult) {
                        RxBus.getDefault().post(new EventSelectReceiverAddress(entity.get()));
                        viewModel.finish();
                    }
                });
    }

}
