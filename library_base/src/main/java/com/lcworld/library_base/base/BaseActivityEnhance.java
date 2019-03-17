package com.lcworld.library_base.base;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.lcworld.library_base.R;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.extension.EventDialog;
import com.lcworld.library_base.global.SPKeyGlobal;
import com.lcworld.library_base.http.ErrorConvention;
import com.lcworld.library_base.http.EventRequestLoadingBox;
import com.lcworld.library_base.router.RouterActivityPath;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.KLog;

import java.util.concurrent.TimeUnit;

public abstract class BaseActivityEnhance<V extends ViewDataBinding, VM extends BaseViewModelEnhance> extends BaseActivity<V, VM> {
    private QMUITipDialog mQMuiDialog_LoadingBox;
    private QMUITipDialog mQMuiDialog_Tip;
    private boolean bForeground;
    private Disposable responseThrowableDisposable;
    private Disposable loadingBoxDisposable;
    protected static final int LOGIN_REQUEST_CODE = 888;
    protected static final int LOGIN_SUCCESS = 200;
    protected static final int LOGIN_FAIL = 404;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Window window = getWindow();
//        //After LOLLIPOP not translucent status bar
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //Then call setStatusBarColor.
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(R.color.qmui_config_color_transparent));

        //隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //状态栏文字设置成黑色
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        //新增对话框回调
        registorDialogControllLiveDataCallBack();
        //注册登录失效和接口异常的RxBus
        registerRxBus();
    }

    @Override
    public void onResume() {
        super.onResume();
        bForeground = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        bForeground = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeRxBus();
    }

    private void registerRxBus() {
        responseThrowableDisposable = RxBus.getDefault().toObservable(ResponseThrowable.class)
                .subscribe(new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable responseThrowable) throws Exception {
                        KLog.d(BaseActivityEnhance.this.getClass().toString(), responseThrowable.message);
                        rxBusDispose_ResponseThrowable(responseThrowable);
                    }
                });

        loadingBoxDisposable = RxBus.getDefault().toObservable(EventRequestLoadingBox.class)
                .subscribe(new Consumer<EventRequestLoadingBox>() {
                    @Override
                    public void accept(EventRequestLoadingBox eventRequestLoadingBox) throws Exception {
                        rxBusDispose_EventRequestLoadingBox(eventRequestLoadingBox);
                    }
                });

        RxSubscriptions.add(loadingBoxDisposable);
        RxSubscriptions.add(responseThrowableDisposable);
    }

    private void removeRxBus() {
        RxSubscriptions.remove(loadingBoxDisposable);
        RxSubscriptions.remove(responseThrowableDisposable);
    }

    private void rxBusDispose_ResponseThrowable(ResponseThrowable responseThrowable) {
        if (!bForeground) {
            return;
        }

        switch (responseThrowable.code) {
            case ErrorConvention.AUTH_ERROR://重新登录
                SPUtils.getInstance().remove(SPKeyGlobal.Key_Account_Access_Token);
                SPUtils.getInstance().remove(SPKeyGlobal.Key_Account_Refresh_Token);
                ARouter.getInstance().build(RouterActivityPath.Account.PAGER_LOGIN)
                        .withBoolean("isAuthError", true)
                        .navigation(this, LOGIN_REQUEST_CODE);
                break;
            case ErrorConvention.PARAMS_ERROR://参数错误
                break;
            default:
                break;

        }
        dialogControllShow(DialogControllTypeInterf.FAILED, responseThrowable.message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOGIN_REQUEST_CODE:
                switch (resultCode) {
                    case LOGIN_SUCCESS:
                        onLoginSuccess();
                        break;
                    default:
                        onLoginFail();
                        break;
                }

            default:
                break;
        }

    }

    protected void onLoginSuccess() {

    }

    protected void onLoginFail() {
        finish();
    }

    private void rxBusDispose_EventRequestLoadingBox(EventRequestLoadingBox eventRequestLoadingBox) {
        if (!bForeground) {
            return;
        }

        if (eventRequestLoadingBox.isbShow()) {
            showDialog(getResources().getString(R.string.dialog_loading));
        } else {
            dismissDialog();
        }
    }


    @Override
    public void showDialog(String title) {
        dismissDialog();
        mQMuiDialog_LoadingBox = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(title)
                .create();

    }

    @Override
    public void dismissDialog() {
        if (mQMuiDialog_LoadingBox != null && mQMuiDialog_LoadingBox.isShowing()) {
            mQMuiDialog_LoadingBox.dismiss();
            mQMuiDialog_LoadingBox = null;
        }
    }

    private void registorDialogControllLiveDataCallBack() {
        viewModel.getDialogLiveData().getDialogControllShowEvent().observe(this, new Observer<EventDialog>() {
            @Override
            public void onChanged(@Nullable EventDialog eventDialog) {
                dialogControllShow(eventDialog.getType(), eventDialog.getTitle());
            }
        });

        viewModel.getDialogLiveData().getDialogControllHidEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                dialogControllHide();
            }
        });
    }

    private void dialogControllHide() {
        if (mQMuiDialog_Tip != null && mQMuiDialog_Tip.isShowing()) {
            mQMuiDialog_Tip.dismiss();
            mQMuiDialog_Tip = null;
        }
    }

    private void dialogControllShow(int position, String content) {
        dialogControllHide();

        switch (position) {
            case DialogControllTypeInterf.SUCCESS://成功
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.FAILED://失败
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.WARNING://警告
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.PICTURE://单独图片
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .create();
                break;
            case DialogControllTypeInterf.TOAST://吐司
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.CUSTOM://自定义
                mQMuiDialog_Tip = new QMUITipDialog.CustomBuilder(this)
                        .setContent(R.layout.popup_custom)
                        .create();
                break;
            default:
                mQMuiDialog_Tip = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(getString(R.string.dialog_loading))
                        .create();
                break;
        }
        mQMuiDialog_Tip.show();

        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        dialogControllHide();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dialogControllHide();
                        KLog.d(throwable.getMessage());
                    }
                });
    }

}
