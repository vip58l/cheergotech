package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1917:33
 * Description
 */
public class activity_list_select_user extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_select_user.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_select_user;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context, 5));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type135, new CallBcak() {
            @Override
            public void delete(int position) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void setOnClickListener(int position) {
                Object o = list.get(position);


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
