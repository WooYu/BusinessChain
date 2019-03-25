package com.lcworld.module_order.widget.paypsw;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcworld.module_order.R;
import com.lcworld.module_order.adapter.KeyboardAdapter;
import com.lcworld.module_order.bean.DataKeyboard;

import java.util.ArrayList;

public class VirtualKeyboardView extends RelativeLayout {
    private Context mContext;
    private ArrayList<DataKeyboard> mValueList;
    private RecyclerView mRvKeyboard;

    private KeyboardAdapter mKeyboardAdapter;
    private KeyboardInputListenre mInputListener;

    public interface KeyboardInputListenre {
        void input(int position, String content);
    }

    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

        View view = View.inflate(context, R.layout.order_layout_paypsw_keyboard, null);
        mValueList = new ArrayList<>();
        mRvKeyboard = view.findViewById(R.id.rv_keyboard);

        initViewRecyclerView();

        addView(view);      //必须要，不然不显示控件
    }

    private void initViewRecyclerView() {
        // 初始化按钮上应该显示的数字
        mValueList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                mValueList.add(new DataKeyboard(DataKeyboard.Type_Figure, String.valueOf(i)));
            } else if (i == 10) {
                mValueList.add(new DataKeyboard(DataKeyboard.Type_Empty, ""));
            } else if (i == 11) {
                mValueList.add(new DataKeyboard(DataKeyboard.Type_Figure, String.valueOf(0)));
            } else {
                mValueList.add(new DataKeyboard(DataKeyboard.Type_Delete, ""));
            }
        }

        mKeyboardAdapter = new KeyboardAdapter(mValueList);
        mKeyboardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (null != mInputListener) {
                    mInputListener.input(position, mKeyboardAdapter.getData().get(position).getContent());
                }
            }
        });

        mRvKeyboard.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRvKeyboard.setAdapter(mKeyboardAdapter);
    }

    public void setInputListener(KeyboardInputListenre mInputListener) {
        this.mInputListener = mInputListener;
    }
}
