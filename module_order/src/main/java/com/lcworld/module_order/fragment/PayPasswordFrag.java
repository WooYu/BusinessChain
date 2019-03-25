package com.lcworld.module_order.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.*;
import com.lcworld.module_order.R;
import com.lcworld.module_order.widget.paypsw.PasswordView;

public class PayPasswordFrag extends DialogFragment {
    private PasswordView pwdView;

    public PasswordCallback mListener;

    public interface PasswordCallback {
        void inputFinish(String pwd);

        void retrievePsw();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.order_layout_paypsw_entrance, container, false);
        pwdView = view.findViewById(R.id.passwordview);
        pwdView.setInputStateListener(new PasswordView.InputStateInterf() {
            @Override
            public void complete(String password) {
                if (null != mListener) {
                    mListener.inputFinish(password);
                }
                dismiss();
            }

            @Override
            public void cancel() {
                dismiss();
            }

            @Override
            public void forgetPsw() {
                if (null != mListener) {
                    mListener.retrievePsw();
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置dialog宽度
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    public void setInputListener(PasswordCallback mListener) {
        this.mListener = mListener;
    }
}
