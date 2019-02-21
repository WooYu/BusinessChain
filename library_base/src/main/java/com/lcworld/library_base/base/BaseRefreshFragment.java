package com.lcworld.library_base.base;

import android.databinding.ViewDataBinding;

public abstract class BaseRefreshFragment<V extends ViewDataBinding, VM extends BaseViewModelEnhance> extends BaseFragmentEnhance<V, VM> {

    public abstract void startRefresh();
}
