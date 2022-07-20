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
import com.cheergotech.ulist.Toasts;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1918:47
 * 事项-租借-归还
 */
public class acetivity_guihuan_lease1 extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_guihuan_lease1.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_guihuan_lease1;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 5; i++) {
            list.add("https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics7.baidu.com%2Ffeed%2F58ee3d6d55fbb2fb8984df0b5f3e8aae4723dcff.jpeg%3Ftoken%3D9b640bb9870e6ad208b7c9464e0b1f94&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1658422800&t=23bdf99f8bc425b7e6a40b2a5134726a");
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type142, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                Toasts.toastMessage("11111");
            }

            @Override
            public void delete(int position) {
                list.remove(position);
                adapter.notifyDataSetChanged();
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
