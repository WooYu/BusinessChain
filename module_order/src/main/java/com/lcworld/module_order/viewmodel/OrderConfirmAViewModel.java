package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableDouble;
import android.databinding.ObservableParcelable;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.*;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class OrderConfirmAViewModel extends BaseViewModelEnhance {
    public final ObservableParcelable<DataMemberAddress> observableAddress = new ObservableParcelable<>();
    public final ObservableArrayList<DataCartVo> observableCartVoList = new ObservableArrayList<>();
    public final ObservableDouble observableTotalPrice = new ObservableDouble();
    public BindingCommand clickOfSubmit = new BindingCommand(new BindingAction(){

        @Override
        public void call() {
            requestCreateOrder();
        }
    });

    public OrderConfirmAViewModel(@NonNull Application application) {
        super(application);

        requestOrderDetail();
        requestOrderBillingInfo();
    }

    //获取结算详情
    public void requestOrderDetail() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsChecked()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataCartView>>() {

                    @Override
                    public void onSuccess(RequestResult<DataCartView> dataCartViewRequestResult) {
                        observableCartVoList.clear();
                        observableCartVoList.addAll(dataCartViewRequestResult.getData().getCart_list());
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

    //创建订单
    private void requestCreateOrder() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCreate(getApplication().getResources().getStringArray(R.array.client_type)[0])
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataTradeVo>>() {

                    @Override
                    public void onSuccess(RequestResult<DataTradeVo> dataTradeVoRequestResult) {
                        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Choose).navigation();
                    }
                });
    }
}
