package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.bean.DataOrderDTO;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class OrderDetailViewModel extends BaseViewModelEnhance {
    public OrderDetailViewModel(@NonNull Application application) {
        super(application);

    }

    public final ObservableField<String> valueOrderId = new ObservableField<>();
    public final ObservableField<DataOrderDTO> valueDataOrderDetail = new ObservableField<>();
    public final BindingCommand clickedBuyAgain = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Product.PAGER_PRODUCTDETAIL)
                    .withInt("goods_id", valueDataOrderDetail.get().getOrder_sku_list().get(0).getGoods_id())
                    .navigation();
        }
    });

    public final BindingCommand clickedPickUp = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //请求提交订单
    public void requestOrderDetail() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeOrdersDetail(valueOrderId.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataOrderDTO>>() {

                    @Override
                    public void onSuccess(RequestResult<DataOrderDTO> dataOrderDTORequestResult) {
                        valueDataOrderDetail.set(dataOrderDTORequestResult.getData());
                    }
                });
    }
}
