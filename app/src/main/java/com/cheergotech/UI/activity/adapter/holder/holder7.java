package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.followerInfo;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.widgetimage5;

import butterknife.BindView;

public class holder7 extends BaseHolder {
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.widgetimage1)
    widgetimage5 w1;
    @BindView(R.id.widgetimage2)
    widgetimage5 w2;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;


    public holder7(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_avaer, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        followerInfo info = (followerInfo) o;
        if (TextUtils.isEmpty(info.getHeadImg())) {
            ImageLoadHelper.glideShowImageWithUrl(context, R.mipmap.avasss, image);
        } else {
            ImageLoadHelper.glideShowImageWithUrl(context, info.getHeadImg(), image);
        }
        del.setVisibility(userInfo.getId() == info.getUserId() ? View.VISIBLE : View.GONE);
        name.setText(info.getNickName());
        msg.setText(info.getDescribes());
        w1.setVisibility(View.GONE);
        w2.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        time.setText(Config.timestamp(String.valueOf(info.getCreateTime()), null));

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
            del.setOnClickListener(view -> callBcak.delete(position));
        }


    }


}
