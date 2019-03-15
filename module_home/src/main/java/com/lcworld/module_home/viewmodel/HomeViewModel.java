package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.activity.MessageListAct;
import com.lcworld.module_home.bean.DataFocusPictures;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.lcworld.module_home.bean.DataSpellDeals;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;

public class HomeViewModel extends BaseViewModelEnhance {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    //点击搜索框
    public BindingCommand clickBgSearch = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Home.Pager_Home_Search).navigation();
        }
    });

    //点击消息
    public BindingCommand clickMessage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MessageListAct.class);
        }
    });

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //轮播图数据变化
        public ObservableArrayList<DataFocusPictures> focusPicturesObservableList = new ObservableArrayList<>();
        //自由购数据变化
        public ObservableArrayList<DataGoodsInfo> freeBuyObserableList = new ObservableArrayList<>();
        //如意赚数据变化
        public ObservableArrayList<DataSpellDeals> spellDealsObservableArrayList = new ObservableArrayList<>();
    }

    //请求轮播图
    public void requestFocusPictures() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .focusPictures(getApplication().getResources().getStringArray(R.array.client_type)[0]
                        , getApplication().getResources().getStringArray(R.array.page_type)[0])
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataFocusPictures>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataFocusPictures>> listRequestResult) {
                        if (!uc.focusPicturesObservableList.isEmpty()) {
                            uc.focusPicturesObservableList.clear();
                        }
                        uc.focusPicturesObservableList.addAll(listRequestResult.getData());
                    }
                });

    }

    //请求自由购
    public void requestFreeBuy() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .tagGoods(getApplication().getResources().getStringArray(R.array.mark_type)[0]
                ,getApplication().getResources().getInteger(R.integer.config_freebuy_displayquants))
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataGoodsInfo>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataGoodsInfo>> listRequestResult) {
                        if (!uc.freeBuyObserableList.isEmpty()) {
                            uc.freeBuyObserableList.clear();
                        }
                        uc.freeBuyObserableList.addAll(listRequestResult.getData());
                    }
                });

    }

    //请求如意赚
    public void requestSpellDeals() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .homePintuan()
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataSpellDeals>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataSpellDeals>> listRequestResult) {
                        if (!uc.spellDealsObservableArrayList.isEmpty()) {
                            uc.spellDealsObservableArrayList.clear();
                        }
                        uc.spellDealsObservableArrayList.addAll(listRequestResult.getData());
                    }
                });

    }


}
