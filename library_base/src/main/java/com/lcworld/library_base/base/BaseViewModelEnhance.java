package com.lcworld.library_base.base;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import com.lcworld.library_base.extension.EventDialog;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

public class BaseViewModelEnhance extends BaseViewModel {

    private DialogLiveData dialogLiveData;


    public BaseViewModelEnhance(@NonNull Application application) {
        super(application);
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
