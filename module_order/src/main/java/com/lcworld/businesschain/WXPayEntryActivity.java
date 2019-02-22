package com.lcworld.businesschain;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.databinding.OrderActivityPaymentResultBinding;
import com.lcworld.module_order.viewmodel.PaymentResultViewModel;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivityEnhance<OrderActivityPaymentResultBinding, PaymentResultViewModel>
        implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.order_activity_payment_result;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

        //TODO:微信参数
        api = WXAPIFactory.createWXAPI(this, "wxd930ea5d5a258f4f");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("WXPayEntryActivity", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage(getString(R.string.order_pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();
        }

        boolean mPayResult = resp.errCode == 0;
        viewModel.valueOfPayResult.set(mPayResult);
        viewModel.valueOfPayResultTip.set(getString(
                mPayResult ? R.string.order_tip_payment_result_success : R.string.order_tip_payment_result_failed));
        binding.ivPicture.setImageResource(mPayResult ? R.mipmap.order_payment_result_success : R.mipmap.order_payment_result_failed);
    }

}