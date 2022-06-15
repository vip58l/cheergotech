package com.cheergotech.UI.activity.baijichu;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 班级圈-我的帖子
 */
public class activity_mylist_bjchu extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_mylist_bjchu.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_bjchu;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter( adapter = new Adapter(context, list, Adapter.bus_baiji, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {

            }

            @Override
            public void OnClick(View view) {

            }
        }));
        findViewById(R.id.add).setOnClickListener(v -> activity_ress.setAction(context));
    }

    @Override
    public void initData() {

    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }
}