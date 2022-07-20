package com.cheergotech.UI.activity.workbench.OAapproval;

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
 * Data 2022/7/1815:44
 * Description 发起审批
 */
public class activity_shengpi extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private static final String TAG = activity_shengpi.class.getName();

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_shengpi.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_shengpi;
    }

    @Override
    public void iniview() {
        String[] table6 = getResources().getStringArray(R.array.table6);
        String[] table7 = getResources().getStringArray(R.array.table7);
        int[] xx = {R.mipmap.x1, R.mipmap.x2, R.mipmap.x3, R.mipmap.x4};

        for (int i = 0; i < table6.length; i++) {
            shengpi shengpi = new shengpi();
            shengpi.setBiticon(i);
            shengpi.setTitle(table6[i]);
            shengpi.setDescribe(table7[i]);
            shengpi.setBiticon(xx[i]);
            list.add(shengpi);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type125, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                shengpi shengpi = (shengpi) list.get(position);
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
