package com.cheergotech.UI.activity.workbench.OAapproval;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1816:39
 * Description 选择调课对象
 */
public class activity_list_caht_select extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.backtitle)
    Backtitle backtitle;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_caht_select.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_caht_user;
    }

    @Override
    public void iniview() {
        backtitle.setconter(getString(R.string.back1));

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type127, new CallBcak() {
            @Override
            public void Onfall() {

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
