package com.lcworld.module_order.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_order.ApiServiceInterf;
import com.lcworld.module_order.BR;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataMemberAddress;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import java.util.List;

public class ReceiverAddressViewModel extends BaseViewModelEnhance {
    //给RecyclerView添加ObservableList
    public ObservableList<ReceiverAddressItemViewModel> observableList = new ObservableArrayList<>();
    //RecyclerView多布局添加ItemBinding
    public ItemBinding<ReceiverAddressItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.order_item_receiveraddr);
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    public final BindingRecyclerViewAdapter<ReceiverAddressItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    public ReceiverAddressViewModel(@NonNull Application application) {
        super(application);
        requestReceiverAddress();
    }

    private void requestReceiverAddress() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersAddresses()
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataMemberAddress>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataMemberAddress>> listRequestResult) {


                        if (null == listRequestResult.getData()) {
                            return;
                        }

                        observableList.clear();
                        for (DataMemberAddress memberAddress : listRequestResult.getData()) {
                            ReceiverAddressItemViewModel itemViewModel = new ReceiverAddressItemViewModel(ReceiverAddressViewModel.this, memberAddress);
                            observableList.add(itemViewModel);
                        }
                        DataMemberAddress memberAddress = new DataMemberAddress();
                        memberAddress.setAddr("dfladfladjfl");
                        memberAddress.setDef_addr(1);
                        memberAddress.setName("2221");
                        memberAddress.setMobile("18627813614");
                        observableList.add(new ReceiverAddressItemViewModel(ReceiverAddressViewModel.this,memberAddress));
                    }
                });
    }
}
