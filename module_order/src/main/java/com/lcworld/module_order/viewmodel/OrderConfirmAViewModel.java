package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableDouble;
import android.databinding.ObservableParcelable;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmAViewModel extends BaseViewModelEnhance {
    private Disposable disposableSelectReceiverAddress;

    public final ObservableParcelable<DataMemberAddress> observableAddress = new ObservableParcelable<>();
    public final ObservableArrayList<DataSKUVo> observableCartVoList = new ObservableArrayList<>();
    public final ObservableDouble observableTotalPrice = new ObservableDouble();
    public BindingCommand clickOfSubmit = new BindingCommand(new BindingAction() {

        @Override
        public void call() {
            turn2Payment();
        }
    });

    public OrderConfirmAViewModel(@NonNull Application application) {
        super(application);

        requestOrderDetail();
        requestOrderBillingInfo();
    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        disposableSelectReceiverAddress = RxBus.getDefault().toObservable(EventSelectReceiverAddress.class)
                .subscribe(new Consumer<EventSelectReceiverAddress>() {
                    @Override
                    public void accept(EventSelectReceiverAddress eventSelectReceiverAddress) throws Exception {
                        observableAddress.set(eventSelectReceiverAddress.getMemberAddress());
                    }
                });
        RxSubscriptions.add(disposableSelectReceiverAddress);
    }

    @Override
    public void removeRxBus() {
        super.removeRxBus();
        RxSubscriptions.remove(disposableSelectReceiverAddress);
    }

    //处理商品展示的数据
    private void processDataGoods(List<DataCartVo> list) {
        if (ObjectUtils.isEmpty(list)) {
            return;
        }

        List<DataSKUVo> skuVoList = new ArrayList<>();
        for (DataCartVo dataCartVo : list) {
            skuVoList.addAll(dataCartVo.getSku_list());
        }

        observableCartVoList.clear();
        observableCartVoList.addAll(skuVoList);
    }

    //跳转到支付
    private void turn2Payment() {
        if(ObjectUtils.isEmpty(observableCartVoList)){
            return;
        }

        if(observableCartVoList.get(0).getGoods_type()
                .equals(getApplication().getResources().getStringArray(R.array.goods_type)[2])){
            //商币支付
            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Diamond).navigation();
        }else{
            //普通商品支付
            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Choose).navigation();
        }
        finish();

    }

    //获取结算详情
    public void requestOrderDetail() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsChecked()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataCartView>>() {

                    @Override
                    public void onSuccess(RequestResult<DataCartView> dataCartViewRequestResult) {
                        processDataGoods(dataCartViewRequestResult.getData().getCart_list());
                        observableTotalPrice.set(dataCartViewRequestResult.getData().getTotal_price().getTotal_price());
                    }
                });
    }

    //获取结算参数
    public void requestOrderBillingInfo() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCheckoutParams()
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataOrderDTO>>() {

                    @Override
                    public void onSuccess(RequestResult<DataOrderDTO> dataOrderDTORequestResult) {
                        requestOrderAddress(dataOrderDTORequestResult.getData().getAddress_id());
                    }
                });
    }

    //获取地址信息
    private void requestOrderAddress(int id) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersAddress(id)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataMemberAddress>>() {

                    @Override
                    public void onSuccess(RequestResult<DataMemberAddress> dataMemberAddressRequestResult) {
                        observableAddress.set(dataMemberAddressRequestResult.getData());
                    }
                });
    }

}
