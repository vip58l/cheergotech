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
 * 事项-已完成-评优查看
 */
public class acetivity_pingyu_tovoid extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_pingyu_tovoid.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_pingyu_tovoid;
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
