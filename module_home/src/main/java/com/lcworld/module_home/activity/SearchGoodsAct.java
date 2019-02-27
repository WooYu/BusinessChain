package com.lcworld.module_home.activity;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lcworld.library_base.base.BaseActivityEnhance;
import com.lcworld.library_base.extension.ListChangedCallbackImpl;
import com.lcworld.library_base.router.RouterActivityPath;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsWords;
import com.lcworld.module_home.bean.DataHotKeyword;
import com.lcworld.module_home.databinding.HomeActivitySearchGoodsBinding;
import com.lcworld.module_home.viewmodel.SearchGoodsViewModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import me.goldze.mvvmhabit.BR;

import java.util.concurrent.TimeUnit;

/**
 * 搜索商品
 */
@Route(path = RouterActivityPath.Home.Pager_Home_Search)
public class SearchGoodsAct extends BaseActivityEnhance<HomeActivitySearchGoodsBinding, SearchGoodsViewModel> {
    private EditText etInputText;

    private TagAdapter<String> mLocalAdapter;
    private TagAdapter<DataHotKeyword> mHotRecomAdapter;
    private BaseQuickAdapter<DataGoodsWords, BaseViewHolder> mResultSuggestAdapter;

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.home_activity_search_goods;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        initViewTitle();
        initObservableLocalRecordList();
        initObservableHotRecomList();
        initObservableSearchSuggestList();

        initViewLocalResultRecyclerView();
        initViewHotRecomRecyclerView();
        initViewResultSuggestRecyclerView();
        initViewSearchBox();
    }

    private void initViewTitle() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initObservableLocalRecordList() {
        viewModel.valueLocalList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mLocalAdapter.notifyDataChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mLocalAdapter.notifyDataChanged();
            }

        });
    }

    private void initObservableHotRecomList() {
        viewModel.valueHotRecomList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mHotRecomAdapter.notifyDataChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mHotRecomAdapter.notifyDataChanged();
            }

        });
    }

    private void initObservableSearchSuggestList() {
        viewModel.valueSuggestList.addOnListChangedCallback(new ListChangedCallbackImpl() {
            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeInserted(sender, positionStart, itemCount);
                mResultSuggestAdapter.setNewData(viewModel.valueSuggestList);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                super.onItemRangeRemoved(sender, positionStart, itemCount);
                mResultSuggestAdapter.setNewData(null);
            }

        });
    }

    private void initViewLocalResultRecyclerView() {
        mLocalAdapter = new TagAdapter<String>(viewModel.valueLocalList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = getLayoutInflater().inflate(R.layout.home_item_search, parent, false);
                TextView textView = view.findViewById(R.id.tv_content);
                textView.setText(s);
                return view;
            }
        };
        binding.flowlayoutHistory.setAdapter(mLocalAdapter);
        binding.flowlayoutHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                turn2SearchGoodsDisplay(viewModel.valueLocalList.get(position));
                return true;
            }
        });
    }

    private void initViewHotRecomRecyclerView() {
        mHotRecomAdapter = new TagAdapter<DataHotKeyword>(viewModel.valueHotRecomList) {
            @Override
            public View getView(FlowLayout parent, int position, DataHotKeyword bean) {
                View view = getLayoutInflater().inflate(R.layout.home_item_search, parent, false);
                TextView textView = view.findViewById(R.id.tv_content);
                textView.setText(bean.getHot_name());
                return view;
            }
        };
        binding.flowlayoutHotRecom.setAdapter(mHotRecomAdapter);
        binding.flowlayoutHotRecom.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                turn2SearchGoodsDisplay(viewModel.valueHotRecomList.get(position).getHot_name());
                return true;
            }
        });
    }

    private void initViewResultSuggestRecyclerView() {
        mResultSuggestAdapter = new BaseQuickAdapter<DataGoodsWords, BaseViewHolder>(R.layout.home_item_search_result) {
            @Override
            protected void convert(BaseViewHolder helper, DataGoodsWords item) {
                helper.setText(R.id.tv_content, item.getWords());
            }
        };
        mResultSuggestAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                turn2SearchGoodsDisplay(mResultSuggestAdapter.getData().get(position).getWords());
            }
        });
        binding.rvResult.setLayoutManager(new LinearLayoutManager(this));
        binding.rvResult.setAdapter(mResultSuggestAdapter);
    }

    private void initViewSearchBox() {
        etInputText = findViewById(R.id.et_search);
        RxTextView.textChanges(etInputText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        boolean isEmpty = charSequence.toString().trim().length() <= 0;
                        if (isEmpty) {
                            viewModel.visiableResult.set(false);
                        }
                        return !isEmpty;
                    } // 过滤，把输入字符串长度为0时过滤掉
                })
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        viewModel.requestSearchResult(charSequence.toString());
                    }
                });

    }

    private void turn2SearchGoodsDisplay(String key) {
        viewModel.updateLocalRecord(key);
        Bundle bundle = new Bundle();
        bundle.putString("searchkey", key);
        startActivity(SearchGoodsResultAct.class, bundle);
    }

}
