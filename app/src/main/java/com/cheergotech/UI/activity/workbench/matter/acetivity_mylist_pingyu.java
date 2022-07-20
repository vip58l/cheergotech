package com.cheergotech.UI.activity.workbench.matter;

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
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1918:47
 * 事项-我参与的事项-评优查看-提交投票跳转此页面并弹框投票成功
 */
public class acetivity_mylist_pingyu extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_mylist_pingyu.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_mylist_pingyu;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type137, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {

            }
        }));

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
