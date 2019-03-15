package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.library_base_kotlin.Cache.LoginCache;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import java.util.List;


public class MineViewModel extends BaseViewModelEnhance {
    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableBoolean isLogin = new ObservableBoolean();
    public ObservableField<String> loginName = new ObservableField<String>("");
    public ObservableField<String> chainid = new ObservableField<String>("商链号：");
    public ObservableField<String> fans = new ObservableField<String>("粉丝0人");
    public ObservableField<String> image = new ObservableField<String>("");
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!LoginCache.INSTANCE.isLogin()) {
                ARouter.getInstance().build(RouterActivityPath.Account.PAGER_LOGIN).navigation();
            }
        }
    });
    public BindingCommand walletOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Exchange.PAGER_WALLET).navigation();
        }
    });

    public BindingCommand backstageOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Backstage.PAGER_ENTRANCE).navigation();
        }
    });
    public BindingCommand toolOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Tool.PAGER_ENTRANCE).navigation();
        }
    });
    public BindingCommand settingOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.System.PAGER_SYSTEM).navigation();
        }
    });
    public BindingCommand loginOutOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            SPUtils.getInstance().clear();
            isLogin.set(false);
        }
    });

    public ObservableArrayList<DataFocusPictures> focusPicturesObservableList = new ObservableArrayList<>();

    //请求轮播图
    public void requestFocusPictures() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .focusPictures(getApplication().getResources().getStringArray(R.array.client_type)[0]
                        , getApplication().getResources().getStringArray(R.array.page_type)[0])
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataFocusPictures>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataFocusPictures>> listRequestResult) {
                        if (!focusPicturesObservableList.isEmpty()) {
                            focusPicturesObservableList.clear();
                        }
                        focusPicturesObservableList.addAll(listRequestResult.getData());
                    }
                });

    }
}
