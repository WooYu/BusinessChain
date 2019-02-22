package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.*;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataOrderProfitDiamond;
import com.lcworld.module_order.bean.DataPintuanPayOrderInfoDTO;
import com.lcworld.module_order.bean.DataProportionDTO;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class OrderConfirmBViewModel extends BaseViewModelEnhance {

    public OrderConfirmBViewModel(@NonNull Application application) {
        super(application);

        requestBenefitsList();
    }


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
            clickSubmitOrder();
        }
    });
    //销售周期的点击事件
    public BindingCommand clickOfSalesCycle = new BindingCommand<>(new BindingAction() {
        @Override
        public void call() {
            uc.showSalesCycle.set(!uc.showSalesCycle.get());
        }
    });

    //销售商品件数的值
    public ObservableInt valueQuantityOfGoods = new ObservableInt();
    //销售周期的值
    public ObservableField<String> valueDaysSales = new ObservableField<>();
    //销售周期列表
    public ObservableList<DataProportionDTO> valuesSaleDayList = new ObservableArrayList<>();
    //选中的销售周期的位置
    public ObservableInt valueSalesDayPosition = new ObservableInt();
    //结算时间的值ObservableInt
    public ObservableInt valueDaySettle = new ObservableInt(1);
    //预期利润的值
    public ObservableField<String> valueProfitRatio = new ObservableField<>("");
    //结算收益商链钻的值
    public ObservableField<String> valueProfitDiamond = new ObservableField<>("");
    //skuid
    public ObservableInt valueSkuId = new ObservableInt();


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
        public ObservableBoolean enableSubmitOrder = new ObservableBoolean(true);
        //减号是否可以点击
        public ObservableBoolean enableSub = new ObservableBoolean(false);
        //加号是否可以点击
        public ObservableBoolean enableAdd = new ObservableBoolean(false);
        //合同明细的选中监听
        public ObservableBoolean checkCompact = new ObservableBoolean(false);
        //销售周期点击减
        public ObservableBoolean showSalesCycle = new ObservableBoolean(false);
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

    private void clickSubmitOrder() {
        if (valuesSaleDayList.isEmpty()) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.order_error_salesday));
            return;
        }

        if (valueQuantityOfGoods.get() <= 0) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.order_error_goodsquantity));
            return;
        }

        if (ObjectUtils.isEmpty(valueDaysSales.get())) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.order_error_salesday));
            return;
        }

        if (!uc.checkCompact.get()) {
            dialogControll_show(DialogControllTypeInterf.WARNING, getApplication().getString(R.string.order_error_compact));
            return;
        }


        requestConfirmOrder(valuesSaleDayList.get(valueSalesDayPosition.get()).getId(), valueQuantityOfGoods.get());
    }

    //请求获取拼团年化收益率列表
    public void requestBenefitsList() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrderGetProportion()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataProportionDTO>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataProportionDTO>> listRequestResult) {
                        valuesSaleDayList.clear();
                        valuesSaleDayList.addAll(listRequestResult.getData());
                    }
                });
    }

    //请求根据销售周期计算收益商链钻
    public void requestCalculateProfit() {
        DataProportionDTO proportionDTO = valuesSaleDayList.get(valueSalesDayPosition.get());
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrderGetProfit(valueSkuId.get(), proportionDTO.getId(), valueQuantityOfGoods.get())
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataOrderProfitDiamond>>() {
                    @Override
                    public void onSuccess(RequestResult<DataOrderProfitDiamond> dataOrderProfitDiamondRequestResult) {
                        valueProfitDiamond.set(dataOrderProfitDiamondRequestResult.getData().getDiamond_num() + "");
                    }
                });
    }

    //请求提交订单
    public void requestConfirmOrder(int proportion_id, int count) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pinTuanOrder(valueSkuId.get(), proportion_id, count)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<DataPintuanPayOrderInfoDTO>>() {
                    @Override
                    public void onSuccess(RequestResult<DataPintuanPayOrderInfoDTO> dataPintuanPayOrderInfoDTORequestResult) {
                        ARouter.getInstance().build(RouterActivityPath.Order.Pager_Payment_Choose).navigation();
                        finish();
                    }
                });
    }

}