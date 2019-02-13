package com.lcworld.library_base.base;

import android.arch.lifecycle.Observer;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import com.lcworld.library_base.R;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.extension.EventDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.KLog;

import java.util.concurrent.TimeUnit;

public abstract class BaseFragmentEnhance<V extends ViewDataBinding, VM extends BaseViewModelEnhance> extends BaseFragment<V, VM> {
    private QMUITipDialog mQMuiDialog;

    @Override
    public void initData() {
        super.initData();
        //新增的私有的ViewModel与View的契约事件回调逻辑
        registorDialogControllLiveDataCallBack();
    }

    @Override
    public void showDialog(String title) {
        dialogControllShow(DialogControllTypeInterf.LOADING, title);
    }

    @Override
    public void dismissDialog() {
        dialogControllHide();
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
        if (mQMuiDialog != null && mQMuiDialog.isShowing()) {
            mQMuiDialog.dismiss();
            mQMuiDialog = null;
        }
    }

    private void dialogControllShow(int position, String content) {
        dialogControllHide();

        switch (position) {
            case DialogControllTypeInterf.LOADING://加载中
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(content)
                        .create();
                mQMuiDialog.show();
                return;
            case DialogControllTypeInterf.SUCCESS://成功
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.FAILED://失败
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.WARNING://警告
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.PICTURE://单独图片
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .create();
                break;
            case DialogControllTypeInterf.TOAST://吐司
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setTipWord(content)
                        .create();
                break;
            case DialogControllTypeInterf.CUSTOM://自定义
                mQMuiDialog = new QMUITipDialog.CustomBuilder(getActivity())
                        .setContent(R.layout.popup_custom)
                        .create();
                break;
            default:
                mQMuiDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(getString(R.string.dialog_loading))
                        .create();
                break;
        }
        mQMuiDialog.show();

        Observable.timer(1500, TimeUnit.MILLISECONDS)
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
