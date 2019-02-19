package com.lcworld.module_order.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.lcworld.module_order.bean.DataMemberAddress;
import me.goldze.mvvmhabit.base.ItemViewModel;

public class ReceiverAddressItemViewModel extends ItemViewModel<ReceiverAddressViewModel> {
    public ObservableField<DataMemberAddress> entity = new ObservableField<>();

    public ReceiverAddressItemViewModel(@NonNull ReceiverAddressViewModel viewModel, DataMemberAddress entity) {
        super(viewModel);
        this.entity.set(entity);
    }

    public ReceiverAddressItemViewModel(@NonNull ReceiverAddressViewModel viewModel) {
        super(viewModel);
    }
}
