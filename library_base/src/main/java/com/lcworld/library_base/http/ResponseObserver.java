package com.lcworld.library_base.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.KLog;

public abstract class ResponseObserver<T extends RequestResult> implements Observer<T> {
    private final String TAG = "ResponseObserver";

    @Override
    public void onSubscribe(Disposable d) {
        KLog.i(TAG, "onSubscribe()");
    }

    @Override
    public void onNext(T t) {
        KLog.i(TAG, "onNext()");
        if (t.isOk()) {
            onSuccess(t);
        } else {
            RequestParamsException requestParamsException = new RequestParamsException();
            requestParamsException.setCode(t.getCode());
            requestParamsException.setMessage(t.getMessage());
            onError(requestParamsException);
        }
    }

    @Override
    public void onError(Throwable t) {
        KLog.e(TAG, "onError()");
        RxBus.getDefault().post(RequestExceptionHandle.handleException(t));
    }

    @Override
    public void onComplete() {
        KLog.d(TAG, "onComplete()");
    }

    public abstract void onSuccess(T t);
}
