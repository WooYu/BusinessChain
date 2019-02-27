package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsInfo;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class SearchGoodsResultViewModel extends BaseViewModelEnhance {

    public SearchGoodsResultViewModel(@NonNull Application application) {
        super(application);
        sortArr = application.getResources().getStringArray(R.array.goods_sort);
        valueSort.set(sortArr[0]);
    }

    private String[] sortArr;

    //搜索的key
    public final ObservableField<String> valueSearchKey = new ObservableField<>();
    //当前数据的分页页码
    public final ObservableInt valuePage = new ObservableInt(1);
    //排序
    public final ObservableField<String> valueSort = new ObservableField<>();
    //商品列表
    public final ObservableArrayList<DataGoodsInfo> valueGoodsList = new ObservableArrayList<>();
    //是否可以加载更多
    public final ObservableBoolean valueEnableLoadMore = new ObservableBoolean();//根据数据大小判断是否加载更多
    //是否可以下拉刷新
    public final ObservableBoolean valueEnableRefresh = new ObservableBoolean();

    //展示模式的选中状态
    public final ObservableBoolean checkStatusDisplayMode = new ObservableBoolean(true);
    //推荐的选中状态
    public final ObservableBoolean checkStatusRecom = new ObservableBoolean(true);
    //销量的选中状态
    public final ObservableBoolean checkStatusSalesVolume = new ObservableBoolean();
    //价格的选中状态
    public final ObservableBoolean checkStatusPrice = new ObservableBoolean();


    //返回键的点击
    public final BindingCommand clickBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    //点击推荐
    public final BindingCommand clickRecom = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            updateValueSortStauts(true, false, false);
        }
    });
    //点击销量
    public final BindingCommand clickSalesVolume = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            updateValueSortStauts(false, true, false);
        }
    });
    //点击价格
    public final BindingCommand clickPrice = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            updateValueSortStauts(false, false, true);
        }
    });

    private void updateValueSortStauts(boolean checkRecom, boolean checkSalesVolume, boolean checkPrice) {
        checkStatusRecom.set(checkRecom);
        checkStatusSalesVolume.set(checkSalesVolume);
        checkStatusPrice.set(checkPrice);
        if (checkRecom) {
            valueSort.set(sortArr[0]);
        } else if (checkSalesVolume) {
            valueSort.set(sortArr[4]);
        } else if (checkPrice) {
            valueSort.set(sortArr[2]);
        }

        requestGoodsList(valuePage.get());
    }

    //请求商品列表
    public void requestGoodsList(int requestPageNo) {
        if (ObjectUtils.isEmpty(valueSearchKey.get())) {
            return;
        }

        if (requestPageNo == 1) {
            valueEnableRefresh.set(true);
        }

        valueEnableLoadMore.set(false);
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsSearch(requestPageNo, getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)
                        , valueSearchKey.get(), null, null, null, valueSort.get(), null, null, null)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataGoodsInfo>>() {

                    @Override
                    public void onSuccess(RequestResultPageImp<DataGoodsInfo> dataGoodsInfoRequestResultPageImp) {
                        DataPage<DataGoodsInfo> dataPage = dataGoodsInfoRequestResultPageImp.getData();
                        int curReturnDataSize = ObjectUtils.isEmpty(dataPage.getData()) ? 0 : dataPage.getData().size();
                        if (dataPage.getPage_no() == 1) {
                            valueEnableLoadMore.set(dataPage.getPage_size() == curReturnDataSize);
                            valuePage.set(dataPage.getPage_no());
                            valueGoodsList.clear();
                            if (curReturnDataSize != 0) {
                                valueGoodsList.addAll(dataPage.getData());
                            }

                        } else {
                            valueEnableLoadMore.set((dataPage.getPage_size() * (dataPage.getPage_no() - 1) + curReturnDataSize) < dataPage.getData_total());
                            if (curReturnDataSize != 0) {
                                valuePage.set(dataPage.getPage_no());
                                valueGoodsList.addAll(dataPage.getData());
                            }

                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        valueEnableRefresh.set(false);
                    }

                });

    }
}
