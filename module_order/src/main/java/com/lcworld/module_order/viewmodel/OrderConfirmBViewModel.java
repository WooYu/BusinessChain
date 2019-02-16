package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataOrderProfitDiamond;
import com.lcworld.module_order.bean.DataProportionDTO;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class OrderConfirmBViewModel extends BaseViewModelEnhance {
    //减号的点击事件
    public BindingCommand clickOfMinus = new BindingCommand<>(new BindingAction() {
        @Override
        public void call() {
            if (valueQuantityOfGoods.get() > 1) {
                valueQuantityOfGoods.set(valueQuantityOfGoods.get() - 1);
            }
        }
    });
    //加号的点击事件
    public BindingCommand clickOfPlus = new BindingCommand<>(new BindingAction() {
        @Override
        public void call() {
            if (valueQuantityOfGoods.get() < getApplication().getResources().getInteger(R.integer.config_goodsquantity_maxlimit)) {
                valueQuantityOfGoods.set(valueQuantityOfGoods.get() + 1);
            }
        }
    });
    //提交订单的点击事件
    public BindingCommand clickOfSubmit = new BindingCommand<>(new BindingAction() {
        @Override
        public void call() {
            requestConfirmOrder(252, 1, valueQuantityOfGoods.get());
        }
    });
    //销售周期的点击事件
    public BindingCommand clickOfSalesCycle = new BindingCommand<>(new BindingAction() {
        @Override
        public void call() {

        }
    });

    //销售商品件数的值
    public ObservableInt valueQuantityOfGoods = new ObservableInt();
    //销售周期的值
    public ObservableInt valueDaysSales = new ObservableInt(0);
    //结算时间的值
    public ObservableInt valueDaySettle = new ObservableInt(1);
    //预期利润的值
    public ObservableDouble valueProfitRatio = new ObservableDouble();
    //结算收益商链钻的值
    public ObservableInt valueProfitDiamond = new ObservableInt();

    //购买数量输入监听
    //密码或者验证码输入监听
    public TextViewBindingAdapter.AfterTextChanged textChangedQuantityGoods() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                checkQuantityGoods();
            }
        };
    }

    //界面变化
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //提交订单是否可以点击
        public ObservableBoolean enableSubmitOrder = new ObservableBoolean(false);
        //减号是否可以点击
        public ObservableBoolean enableSub = new ObservableBoolean(false);
        //加号是否可以点击
        public ObservableBoolean enableAdd = new ObservableBoolean(false);
        //合同明细的选中监听
        public ObservableBoolean checkCompact = new ObservableBoolean(false);
    }

    public OrderConfirmBViewModel(@NonNull Application application) {
        super(application);
    }

    private void checkQuantityGoods() {
        int quantity = valueQuantityOfGoods.get();
        if (quantity == 0) {
            valueQuantityOfGoods.set(1);
        }

        int maxlimit = getApplication().getResources().getInteger(R.integer.config_goodsquantity_maxlimit);
        if (quantity > maxlimit) {
            valueQuantityOfGoods.set(maxlimit);
        }
    }

    //请求获取拼团年化收益率列表
    public void requestBenefitsList() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrderGetProportion()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataProportionDTO>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataProportionDTO>> listRequestResult) {

                    }
                });
    }

    //请求根据销售周期计算收益商链钻
    public void requestBenefitsList(int sku_id, int proportion_id, int count) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrderGetProfit(sku_id, proportion_id, count)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataOrderProfitDiamond>>() {
                    @Override
                    public void onSuccess(RequestResult<DataOrderProfitDiamond> dataOrderProfitDiamondRequestResult) {

                    }
                });
    }

    //请求提交订单
    public void requestConfirmOrder(int sku_id, int proportion_id, int count) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrder(sku_id, proportion_id, count)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResultImp>() {
                    @Override
                    public void onSuccess(RequestResultImp requestResultImp) {

                    }
                });
    }

}