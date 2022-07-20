package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.shengpi;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1916:30
 * 新建事项
 */
public class activity_Newitem extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Newitem.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_newitem;
    }

    @Override
    public void iniview() {

        String[] table8 = getResources().getStringArray(R.array.table8);
        String[] table9 = getResources().getStringArray(R.array.table9);
        int[] sss = {R.mipmap.ss3, R.mipmap.ss2, R.mipmap.ss1};
        for (int i = 0; i < table8.length; i++) {
            String name = table8[i];
            String describe = table9[i];

            shengpi shengpi = new shengpi();
            shengpi.setTitle(name);
            shengpi.setDescribe(describe);
            shengpi.setBiticon(sss[i]);
            list.add(shengpi);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type133, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                shengpi shengpi = (com.cheergotech.UI.model.shengpi) list.get(position);
                Toasts.toastMessage(shengpi.getTitle());
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
