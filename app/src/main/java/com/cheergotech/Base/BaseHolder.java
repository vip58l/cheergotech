package com.cheergotech.Base;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.UI.model.UserInfo;
import com.cheergotech.listen.CallBcak;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    public Object object;
    public Context context;
    public CallBcak callBcak;
    public Unbinder bind;
    public UserInfo userInfo;

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        bind = ButterKnife.bind(this, itemView);
        userInfo = UserInfo.getInstance();
        if (context == null) {
            context = DemoApplication.instance();
        }
    }

    public abstract void bind(Object o, int position, CallBcak callBcak);
}
