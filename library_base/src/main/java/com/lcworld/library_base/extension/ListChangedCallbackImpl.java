package com.lcworld.library_base.extension;

import android.databinding.ObservableList;
import me.goldze.mvvmhabit.utils.KLog;

public class ListChangedCallbackImpl<T extends ObservableList> extends ObservableList.OnListChangedCallback<T>{
    @Override
    public void onChanged(ObservableList sender) {
        KLog.d("onChanged()");
    }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        KLog.d("onItemRangeChanged()");
    }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        KLog.d("onItemRangeInserted()");
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        KLog.d("onItemRangeMoved()");
    }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        KLog.d("onItemRangeRemoved()");
    }
}
