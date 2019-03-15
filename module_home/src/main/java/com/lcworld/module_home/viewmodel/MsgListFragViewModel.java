package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.*;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataMessageNoticeLog;

public class MsgListFragViewModel extends BaseViewModelEnhance {

    public MsgListFragViewModel(@NonNull Application application) {
        super(application);
    }

    public final ObservableInt valueMsgType = new ObservableInt();
    //当前数据的分页页码
    public final ObservableInt valuePage = new ObservableInt(1);
    //消息列表
    public final ObservableArrayList<DataMessageNoticeLog> valueMsgList = new ObservableArrayList<>();
    //是否可以加载更多
    public final ObservableBoolean valueEnableLoadMore = new ObservableBoolean();//根据数据大小判断是否加载更多
    //是否可以下拉刷新
    public final ObservableBoolean valueEnableRefresh = new ObservableBoolean();

    public void requestMsgList(int requestPageNo) {

        if (requestPageNo == 1) {
            valueEnableRefresh.set(true);
        }

        valueEnableLoadMore.set(false);
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .membersMemberNociceLogs(getApplication().getResources().getIntArray(R.array.config_isread)[0], requestPageNo
                        , getApplication().getResources().getInteger(R.integer.config_pageData_defaultSize)
                        , valueMsgType.get())
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResultPageImp<DataMessageNoticeLog>>() {

                    @Override
                    public void onSuccess(RequestResultPageImp<DataMessageNoticeLog> dataMessageNoticeLogRequestResultPageImp) {
                        DataPage<DataMessageNoticeLog> dataPage = dataMessageNoticeLogRequestResultPageImp.getData();
                        int curReturnDataSize = ObjectUtils.isEmpty(dataPage.getData()) ? 0 : dataPage.getData().size();
                        if (dataPage.getPage_no() == 1) {
                            valueEnableLoadMore.set(dataPage.getPage_size() == curReturnDataSize);
                            valuePage.set(dataPage.getPage_no());
                            valueMsgList.clear();
                            if (curReturnDataSize != 0) {
                                valueMsgList.addAll(dataPage.getData());
                            }

                        } else {
                            valueEnableLoadMore.set((dataPage.getPage_size() * (dataPage.getPage_no() - 1) + curReturnDataSize) < dataPage.getData_total());
                            if (curReturnDataSize != 0) {
                                valuePage.set(dataPage.getPage_no());
                                valueMsgList.addAll(dataPage.getData());
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
