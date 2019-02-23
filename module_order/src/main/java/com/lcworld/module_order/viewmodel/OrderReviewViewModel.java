package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;

import java.util.Calendar;

public class OrderReviewViewModel extends BaseViewModelEnhance {
    public OrderReviewViewModel(@NonNull Application application) {
        super(application);

    }

    public final ObservableField<Calendar> valueSelectCalendar = new ObservableField<>();
}
