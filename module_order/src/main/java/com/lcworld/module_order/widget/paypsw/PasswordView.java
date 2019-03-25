package com.lcworld.module_order.widget.paypsw;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.module_order.R;

public class PasswordView extends ConstraintLayout {

    Context mContext;
    private TextView[] tvList;

    private int currentIndex = -1;    //用于记录当前输入密码格位置
    private VirtualKeyboardView mKeyboard;
    private TextView mTvFigureE;
    private TextView mTvFigureF;
    private ImageView mIvCancel;
    private TextView mTvForgetpsw;
    private TextView mTvFigureA;
    private TextView mTvFigureB;
    private TextView mTvFigureC;
    private TextView mTvFigureD;

    private InputStateInterf mInputStateListener;

    public interface InputStateInterf {
        void complete(String password);

        void cancel();

        void forgetPsw();
    }

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.order_layout_paypsw_view, this, true);

        mKeyboard = view.findViewById(R.id.keyboard);
        mTvFigureE = view.findViewById(R.id.tv_figureE);
        mTvFigureF = view.findViewById(R.id.tv_figureF);
        mIvCancel = view.findViewById(R.id.iv_cancel);
        mTvForgetpsw = view.findViewById(R.id.tv_forgetpsw);
        mTvFigureA = view.findViewById(R.id.tv_figureA);
        mTvFigureB = view.findViewById(R.id.tv_figureB);
        mTvFigureC = view.findViewById(R.id.tv_figureC);
        mTvFigureD = view.findViewById(R.id.tv_figureD);

        initView();
    }

    private void initView() {
        tvList = new TextView[]{mTvFigureA, mTvFigureB, mTvFigureC, mTvFigureD, mTvFigureE, mTvFigureF};

        mTvFigureF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (ObjectUtils.isEmpty(content)) {
                    return;
                }

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    builder.append(tvList[i].getText().toString().trim());
                }
                if (null != mInputStateListener) {
                    mInputStateListener.complete(builder.toString());
                }
            }
        });

        mKeyboard.setInputListener(new VirtualKeyboardView.KeyboardInputListenre() {
            @Override
            public void input(int position, String content) {
                if (position < 11 && position != 9) {    //点击0~9按钮
                    if (currentIndex >= -1 && currentIndex < 5) {      //判断输入位置————要小心数组越界
                        ++currentIndex;
                        tvList[currentIndex].setText(content);
                    }
                } else if (position == 11) {      //点击退格键
                    if (currentIndex - 1 >= -1) {      //判断是否删除完毕————要小心数组越界
                        tvList[currentIndex].setText("");
                        currentIndex--;
                    }
                }

            }
        });

        mIvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mInputStateListener) {
                    mInputStateListener.cancel();
                }
            }
        });

        mTvForgetpsw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mInputStateListener) {
                    mInputStateListener.forgetPsw();
                }
            }
        });
    }

    public void setInputStateListener(InputStateInterf listener) {
        this.mInputStateListener = listener;
    }

}

