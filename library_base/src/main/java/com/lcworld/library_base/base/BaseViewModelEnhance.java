package com.lcworld.library_base.base;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import com.lcworld.library_base.extension.DialogControllTypeInterf;
import com.lcworld.library_base.extension.EventDialog;
import com.lcworld.library_base.http.ErrorConvention;
import com.lcworld.library_base.http.EventRequestLoadingBox;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.KLog;

public class BaseViewModelEnhance extends BaseViewModel {
    private Disposable responseThrowableDisposable;
    private Disposable loadingBoxDisposable;
    private DialogLiveData dialogLiveData;

    public BaseViewModelEnhance(@NonNull Application application) {
        super(application);
    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        responseThrowableDisposable = RxBus.getDefault().toObservable(ResponseThrowable.class)
                .subscribe(new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable responseThrowable) throws Exception {
                        KLog.d(responseThrowable.message);
                        switch (responseThrowable.code) {
                            case ErrorConvention.AUTH_ERROR://重新登录
                                break;
                            case ErrorConvention.PARAMS_ERROR://参数错误
                                break;
                            default:
                                break;

                        }
                        dialogControll_show(DialogControllTypeInterf.FAILED, responseThrowable.message);
                    }
                });

        loadingBoxDisposable = RxBus.getDefault().toObservable(EventRequestLoadingBox.class)
                .subscribe(new Consumer<EventRequestLoadingBox>() {
                    @Override
                    public void accept(EventRequestLoadingBox eventRequestLoadingBox) throws Exception {
                        if (eventRequestLoadingBox.isbShow()) {
                            showDialog();
                        } else {
                            dismissDialog();
                        }
                    }
                });

        RxSubscriptions.add(loadingBoxDisposable);
        RxSubscriptions.add(responseThrowableDisposable);
    }

    @Override
    public void removeRxBus() {
        super.removeRxBus();
        RxSubscriptions.remove(loadingBoxDisposable);
        RxSubscriptions.remove(responseThrowableDisposable);
    }

    public DialogLiveData getDialogLiveData() {
        if (dialogLiveData == null) {
            dialogLiveData = new DialogLiveData();
        }
        return dialogLiveData;
    }

    public void dialogControll_show(int type, String message) {
        this.dialogLiveData.dialogControllShowEvent.postValue(new EventDialog(type, message));
    }

    public void dialogControll_hid() {
        this.dialogLiveData.dialogControllHidEvent.call();
    }

    public final class DialogLiveData extends SingleLiveEvent {
        private SingleLiveEvent<EventDialog> dialogControllShowEvent;
        private SingleLiveEvent<Void> dialogControllHidEvent;

        public SingleLiveEvent<EventDialog> getDialogControllShowEvent() {
            return dialogControllShowEvent = createLiveData(dialogControllShowEvent);
        }

        public SingleLiveEvent<Void> getDialogControllHidEvent() {
            return dialogControllHidEvent = createLiveData(dialogControllHidEvent);
        }

        private SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent();
            }
            return liveData;
        }

        @Override
        public void observe(LifecycleOwner owner, Observer observer) {
            super.observe(owner, observer);
        }
    }

}
