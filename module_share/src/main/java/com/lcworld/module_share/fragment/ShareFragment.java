package com.lcworld.module_share.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.router.RouterFragmentPath;
import com.lcworld.module_share.DataShareCategory;
import com.lcworld.module_share.R;
import me.goldze.mvvmhabit.utils.KLog;

import java.util.ArrayList;
import java.util.HashMap;

@Route(path = RouterFragmentPath.Share.Pager_Entrance)
public class ShareFragment extends DialogFragment implements PlatformActionListener {
    private TextView mTvCancel;
    private RecyclerView mRecyclerView;

    private BaseQuickAdapter<DataShareCategory, BaseViewHolder> mShareAdapter;
    private int[] imageArr = new int[]{R.mipmap.share_item_wechat, R.mipmap.share_item_wechat_moment
            , R.mipmap.share_item_qq, R.mipmap.share_item_qqzone};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.share_frag_dialog, container);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置dialog宽度
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = win.getAttributes();
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        win.setAttributes(params);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        KLog.d("分享完成");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        KLog.e(throwable.getMessage());
    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    private void initView() {
        String[] categoryArr = getResources().getStringArray(R.array.share_category);
        ArrayList<DataShareCategory> datalist = new ArrayList<>();
        for (int i = 0; i < categoryArr.length; i++) {
            datalist.add(new DataShareCategory(categoryArr[i], imageArr[i]));
        }

        mShareAdapter = new BaseQuickAdapter<DataShareCategory, BaseViewHolder>(R.layout.share_item_category, datalist) {
            @Override
            protected void convert(BaseViewHolder helper, DataShareCategory item) {
                helper.setText(R.id.name, item.getName());
                GlideApp.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.img));
            }
        };
        mShareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                shareByPlatomImg(mShareAdapter.getData().get(position).getImage());
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mShareAdapter);

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void shareByPlatomImg(int resid) {
        dismiss();
        if (resid == R.mipmap.share_item_wechat) {
            share2WeChat();
        } else if (resid == R.mipmap.share_item_wechat_moment) {
            share2WeChatFriend();
        } else if (resid == R.mipmap.share_item_qq) {
            share2QQ();
        } else if (resid == R.mipmap.share_item_qqzone) {
            share2QZone();
        }

    }

    private void share2WeChat() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_TEXT);//非常重要：一定要设置分享属性
        sp.setTitle(getString(R.string.app_name));  //分享标题
        sp.setText("分享文本");   //分享文本(朋友圈不显示）

        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        if (!wechat.isClientValid()) {
            ToastUtils.showShort("请安装选择的分享客户端！");
            return;
        }
        wechat.setPlatformActionListener(this); // 设置分享事件回调
        wechat.share(sp);
    }

    private void share2WeChatFriend() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_TEXT); //非常重要：一定要设置分享属性
        sp.setTitle(getString(R.string.app_name));  //分享标题
        sp.setText("分享文本");

        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        if (!wechatMoments.isClientValid()) {
            ToastUtils.showShort("请安装选择的分享客户端！");
            return;
        }
        wechatMoments.setPlatformActionListener(this); // 设置分享事件回调
        wechatMoments.share(sp);
    }

    private void share2QQ() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(getString(R.string.app_name));
        sp.setTitleUrl("www.baidu.com");  //网友点进链接后，可以看到分享的详情
        sp.setText("分享到QQ");

        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(this); // 设置分享事件回调
        qq.share(sp);
    }

    private void share2QZone() {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(getString(R.string.app_name));
        sp.setTitleUrl("http://www.baidu.com");
        sp.setText("分享到QQ空间");
        sp.setSite("");//网络图片rul
        sp.setSiteUrl("http://www.mob.com");  //网友点进链接后，可以看到分享的详情

        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(this); // 设置分享事件回调
        qq.share(sp);
    }
}
