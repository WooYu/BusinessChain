package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.activity.ReceiverAddressEditAct;
import com.lcworld.module_order.bean.DataMemberAddress;
import com.lcworld.module_order.bean.EventSelectReceiverAddress;
import com.lcworld.module_order.bean.EventUpdateReceiverAddress;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.util.List;

public class ReceiverAddressViewModel extends BaseViewModelEnhance {
    private Disposable updateAddressDisposable;

    //给RecyclerView添加ObservableList
    public ObservableList<DataMemberAddress> observableList = new ObservableArrayList<>();

    //新建地址的点击事件
    public final BindingCommand clickOfCreateAddr = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ReceiverAddressEditAct.class);
        }
    });

    public ReceiverAddressViewModel(@NonNull Application application) {
        super(application);
        requestReceiverAddress();
    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        updateAddressDisposable = RxBus.getDefault().toObservable(EventUpdateReceiverAddress.class)
                .subscribe(new Consumer<EventUpdateReceiverAddress>() {
                    @Override
                    public void accept(EventUpdateReceiverAddress eventUpdateReceiverAddress) throws Exception {
                        requestReceiverAddress();
                    }
                });
        RxSubscriptions.add(updateAddressDisposable);
    }

    @Override
    public void removeRxBus() {
        super.removeRxBus();
        RxSubscriptions.remove(updateAddressDisposable);
    }

    //请求收货地址列表
    private void requestReceiverAddress() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersAddresses()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataMemberAddress>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataMemberAddress>> listRequestResult) {


                        if (null == listRequestResult.getData()) {
                            return;
                        }

                        observableList.clear();
                        observableList.addAll(listRequestResult.getData());
                    }
                });
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

    //请求删除地址
    public void requestMemberAddressRemove(int id) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersAddressDelete(id)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {

                    }

                });
    }

    //请求设置收货地址
    public void requestSetMemberAddress(final int position) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCheckoutParamsAddressId(observableList.get(position).getAddr_id())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        RxBus.getDefault().post(new EventSelectReceiverAddress(observableList.get(position)));
                        finish();
                    }
                });
    }
}
