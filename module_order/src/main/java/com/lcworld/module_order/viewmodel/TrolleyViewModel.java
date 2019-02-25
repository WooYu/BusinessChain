package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.library_base.http.*;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataCartView;
import com.lcworld.module_order.bean.DataCartVo;
import com.lcworld.module_order.bean.DataSKUVo;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.ArrayList;
import java.util.List;

public class TrolleyViewModel extends BaseViewModelEnhance {

    public TrolleyViewModel(@NonNull Application application) {
        super(application);

        valueTitleRight.set(application.getString(R.string.order_trolley_subtitle1));
        requestShoppingCartList();
    }

    //标题右侧
    public final ObservableField<String> valueTitleRight = new ObservableField<>();
    //购物车的状态：展示列表或编辑,false(展示)
    public final ObservableBoolean valueTrolleyEdit = new ObservableBoolean();
    //购物车列表
    public final ObservableArrayList<DataSKUVo> valueTrolleyList = new ObservableArrayList<>();
    //全选按钮的状态
    public final ObservableBoolean valueAllChecked = new ObservableBoolean();
    //商品价格总计
    public final ObservableField<String> valueTotalPrice = new ObservableField<>();

    //全选按钮的监听
    public final BindingCommand clickedAllChecked = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestUpdateAllCheckStatus();
        }
    });
    //删除按钮的点击
    public final BindingCommand clickedDelete = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requetDeleteGoods();
        }
    });
    //结算按钮的点击
    public final BindingCommand clickedSettle = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Order.Pager_Order_Confirm1)
                    .navigation();
        }
    });


    //处理商品展示的数据
    private void processDataGoods(List<DataCartVo> list) {
        if (ObjectUtils.isEmpty(list)) {
            valueTrolleyList.clear();
            return;
        }

        List<DataSKUVo> skuVoList = new ArrayList<>();
        for (DataCartVo dataCartVo : list) {
            skuVoList.addAll(dataCartVo.getSku_list());
        }

        valueTrolleyList.clear();
        valueTrolleyList.addAll(skuVoList);
    }

    //获取购物车列表
    public void requestShoppingCartList() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsAll()
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataCartView>>() {

                    @Override
                    public void onSuccess(RequestResult<DataCartView> dataCartViewRequestResult) {
                        processDataGoods(dataCartViewRequestResult.getData().getCart_list());
                        valueTotalPrice.set(String.format(getApplication().getString(R.string.format_money)
                                , ConvertExUtils.formatMoney(dataCartViewRequestResult.getData().getTotal_price().getTotal_price())));
                    }
                });
    }

    //改变商品信息的选中状态
    public void requestUpdateGoodsChecked(int sku_id, final int checked) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsSkuUpdate(sku_id, String.valueOf(checked), "")
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        if (checked == getApplication().getResources().getIntArray(R.array.config_trolley_checkstatus)[0]) {
                            valueAllChecked.set(false);
                        }
                        requestShoppingCartList();
                    }
                });
    }

    //改变商品信息的数量
    public void requestUpdateGoodsNum(int sku_id, int num) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsSkuUpdate(sku_id, "", String.valueOf(num))
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        requestShoppingCartList();
                    }
                });
    }

    //点击了全选按钮
    public void requestUpdateAllCheckStatus() {
        int checked = getApplication().getResources().getIntArray(R.array.config_trolley_checkstatus)[valueAllChecked.get() ? 1 : 0];
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsChecked(checked)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        requestShoppingCartList();
                    }
                });
    }

    //请求删除商品
    private void requetDeleteGoods() {

        if (valueTrolleyList.isEmpty()) {
            return;
        }

        StringBuilder sBuilder = new StringBuilder();

        int[] checkStatusArr = getApplication().getResources().getIntArray(R.array.config_trolley_checkstatus);
        for (DataSKUVo bean : valueTrolleyList) {
            if (checkStatusArr[1] == bean.getChecked()) {
                sBuilder.append(bean.getSku_id())
                        .append(getApplication().getString(R.string.symbol_comma));
            }
        }

        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tradeCartsSkuDelete(ConvertExUtils.removeLastComma(getApplication(), sBuilder.toString()))
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {

                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {
                        requestShoppingCartList();
                    }
                });
    }
}
