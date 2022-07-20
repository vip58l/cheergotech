package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1910:14
 * OA审批-查看
 */
public class activity_list_myall_qinjiao extends BaseActivity {

    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_myall_qinjiao.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_myall_qinjiao;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 5; i++) {
            list.add("https://pic.rmb.bdstatic.com/bjh/down/f8fa8fb5ae31852912e06ccfa3cdaaf2.jpeg@wm_2,t_55m+5a625Y+3L+ecn+WunuaVheS6i+iuoeWIkg==,fc_ffffff,ff_U2ltSGVp,sz_27,x_17,y_17");
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img2, new CallBcak() {
            @Override
            public void delete(int position) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void setOnClickListener(int position) {

                Object o = list.get(position);
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
