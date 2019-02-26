package com.lcworld.module_home.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lcworld.library_base.base.BaseViewModelEnhance;
import com.lcworld.library_base.global.SPKeyGlobal;
import com.lcworld.library_base.http.RequestResult;
import com.lcworld.library_base.http.ResponseObserver;
import com.lcworld.library_base.http.RetrofitClient;
import com.lcworld.library_base.http.RxUtilsEnhanced;
import com.lcworld.module_home.ApiServiceInterf;
import com.lcworld.module_home.bean.DataGoodsWords;
import com.lcworld.module_home.bean.DataHotKeyword;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.KLog;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;

public class SearchGoodsViewModel extends BaseViewModelEnhance {
    public SearchGoodsViewModel(@NonNull Application application) {
        super(application);

        requestLocalRecord();
        requestHotSearchRecom();
    }

    //最大的历史记录数量
    public final int SearchMaxSize = 10;

    //本地搜索记录
    HashMap<String, String> localRecordHashMap = new LinkedHashMap<String, String>(SearchMaxSize, 0.5f, true) {

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            if (size() > SearchMaxSize) {
                return true;
            }
            return false;
        }
    };

    //是否显示搜索结果
    public final ObservableBoolean visiableResult = new ObservableBoolean();
    //本地搜索记录
    public final ObservableArrayList<String> valueLocalList = new ObservableArrayList<>();
    //热门搜索记录
    public final ObservableArrayList<DataHotKeyword> valueHotRecomList = new ObservableArrayList<>();
    //搜索结果
    public final ObservableArrayList<DataGoodsWords> valueSuggestList = new ObservableArrayList<>();

    //删除的点击事件
    public final BindingCommand clickOfRemoveLocalRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            localRecordHashMap.clear();
            disposeMap2List();
            SPUtils.getInstance().remove(SPKeyGlobal.Key_Search_LocalRecord);
        }
    });

    //遍历Map获取本地数据List
    public void disposeMap2List() {
        if (localRecordHashMap.isEmpty()) {
            valueLocalList.clear();
            return;
        }

        ArrayList<String> list = new ArrayList();
        for (Map.Entry<String, String> entry : localRecordHashMap.entrySet()) {
            list.add(entry.getValue());
        }
        valueLocalList.addAll(list);
    }

    public void disposeList2Json() {
        if (valueLocalList.isEmpty()) {
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for (String s : valueLocalList) {
            jsonArray.put(s);
        }

        KLog.d("本地搜索记录存储：" + jsonArray.toString());
        SPUtils.getInstance().put(SPKeyGlobal.Key_Search_LocalRecord, jsonArray.toString());
    }

    //请求本地搜索
    public void requestLocalRecord() {
        String localRecordJson = SPUtils.getInstance().getString(SPKeyGlobal.Key_Search_LocalRecord);
        if (ObjectUtils.isEmpty(localRecordJson)) {
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(localRecordJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                localRecordHashMap.put(jsonArray.getString(i), jsonArray.getString(i));
            }
            KLog.d("本地搜索记录解析结果：" + localRecordJson);
            disposeMap2List();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //请求热搜推荐
    public void requestHotSearchRecom() {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .pagesHotKeywords(SearchMaxSize)
                .compose(RxUtilsEnhanced.implicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataHotKeyword>>>() {

                    @Override
                    public void onSuccess(RequestResult<List<DataHotKeyword>> listRequestResult) {
                        valueHotRecomList.clear();
                        valueHotRecomList.addAll(listRequestResult.getData());
                    }

                });

    }

    //请求搜索结果
    public void requestSearchResult(final String key) {
        RetrofitClient.getInstance().create(ApiServiceInterf.class)
                .goodsSearchWords(key)
                .compose(RxUtilsEnhanced.explicitTransform())
                .subscribe(new ResponseObserver<RequestResult<List<DataGoodsWords>>>() {
                    @Override
                    public void onSuccess(RequestResult<List<DataGoodsWords>> listRequestResult) {
                        if (localRecordHashMap.containsKey(key)) {
                            localRecordHashMap.get(key);
                        } else {
                            localRecordHashMap.put(key, key);
                        }
                        disposeMap2List();

                        valueSuggestList.clear();
                        valueSuggestList.addAll(listRequestResult.getData());

                        visiableResult.set(ObjectUtils.isNotEmpty(listRequestResult.getData()));
                    }
                });

    }
}
