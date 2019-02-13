package com.lcworld.library_base.widget.textview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.lcworld.library_base.R;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class OnlyTextTab extends BaseTabItem {

    private final TextView mTitle;

    public OnlyTextTab(Context context, String title) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_only_text, this, true);
        mTitle = findViewById(R.id.title);
        mTitle.setText(title);
    }

    @Override
    public void setChecked(boolean checked) {
        mTitle.setTextColor(checked ? 0xFFF4B400 : 0x56000000);
    }

    @Override
    public void setMessageNumber(int number) {
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDefaultDrawable(Drawable drawable) {

    }

    @Override
    public void setSelectedDrawable(Drawable drawable) {

    }

    @Override
    public String getTitle() {
        return mTitle.getText().toString();
    }
}
