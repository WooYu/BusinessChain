package com.lcworld.module_exchange

import com.lcworld.library_base.http.RequestExceptionHandle
import com.lcworld.library_base.http.RequestParamsException
import com.lcworld.library_base.http.RequestResult
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import me.goldze.mvvmhabit.bus.RxBus
import me.goldze.mvvmhabit.utils.KLog

internal val onNextStub: (Any)->Unit = {}
internal val onStartStub: ()->Unit = {}
internal val onCompleteStub: ()->Unit = {}
val logExceptionHandler: (Throwable) -> Unit = { throwable: Throwable -> KLog.e(TAG, "onError()  $throwable") }
internal val onErrorStub: (Throwable) -> Unit = logExceptionHandler
const val TAG = "ResponseObserver"
fun <T : Any> Observable<T>.wrapSubscribe(onNext:(T)->Unit = onNextStub,
                                          onError:(Throwable)->Unit = onErrorStub,
                                          onStart:()->Unit = onStartStub,
                                          onComplete:()->Unit = onCompleteStub){
    val observable = object:Observer<T>{
        override fun onComplete() {
            KLog.d(TAG, "onComplete()")
            onComplete()
        }

        override fun onSubscribe(d: Disposable) {
            KLog.i(TAG, "onSubscribe()")
            onStart()
        }

        override fun onNext(t: T) {
            KLog.i(TAG, "onNext()")
            if(t is RequestResult<*>){
                if (t.isOk) {
                    onNext(t)
                } else {
                    val requestParamsException = RequestParamsException()
                    requestParamsException.code = t.code
                    requestParamsException.setMessage(t.message)
                    onError(requestParamsException)
                }
            }

        }

        override fun onError(e: Throwable) {
            onError(e)
            RxBus.getDefault().post(RequestExceptionHandle.handleException(e))
        }

    }
    subscribe(observable)
}

