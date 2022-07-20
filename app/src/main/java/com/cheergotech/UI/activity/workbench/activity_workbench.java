package com.cheergotech.UI.activity.workbench;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.workbench.folder.activity_folder_main;
import com.cheergotech.UI.activity.workbench.matter.acetivity_guihuan_lease;
import com.cheergotech.UI.activity.workbench.matter.acetivity_guihuan_lease1;
import com.cheergotech.UI.activity.workbench.matter.activity_yqdbsx;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 工作台
 */
public class activity_workbench extends BaseActivity {
    private static final String TAG = activity_workbench.class.getSimpleName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_workbench.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_workbench;
    }

    @Override
    public void iniview() {
        String[] stringArray = getResources().getStringArray(R.array.table4);
        for (String title : stringArray) {
            list.add(title);
            Log.d(TAG, "iniview: " + title);
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.workbenchlist, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                String name = (String) list.get(position);
                Log.d(TAG, "setOnClickListener: " + name);
                activity_folder_main.setAction(context);
            }

            @Override
            public void Onfall() {

            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.clockin})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.clockin:
                //activity_clockin.setAction(activity);
                //activity_shengpi.setAction(activity);
                //activity_list_caht_user.setAction(activity);
                //activity_reimbursement.setAction(activity);
                //activity_bksq.setAction(activity);
                //activity_selecclass.setAction(activity);
                //activity_list_caht_select.setAction(activity);
                //activity_select_quer.setAction(activity);
                //activity_list_caht_caosheng.setAction(activity);
                //activity_lease.setAction(context);
                //activity_list_pending.setAction(context);
                //activity_list_qinjia.setAction(context);
                //activity_list_myall.setAction(context);
                //activity_list_myall_bxiao.setAction(context);
                //activity_list_myall_buka.setAction(context);
                //activity_list_myall_chaoshong.setAction(context);
                //activity_list_myall_qinjiao.setAction(context);
                //activity_outsideschool.setAction(context);
                //activity_Newitem.setAction(context);
                //activity_chat_list.setAction(context);
                //activity_meeting1.setAction(context);
                //activity_meeting2.setAction(context);
                //activity_list_select_user.setAction(context);
                //activity_myfqdsx.setAction(context);
                //acetivity_kuihuiyi.setAction(context);
                //acetivity_pingyu.setAction(context);
                //acetivity_activities.setAction(context);
                //activity_mycydsx.setAction(context);
                //acetivity_activities2.setAction(context);
                //acteivity_location.setAction(context);
                //acetivity_pingyulist.setAction(context);
                //acetivity_mylist_pingyu.setAction(context);
                //acetivity_my_pingyu.setAction(context);
                //activity_completed.setAction(context);
                //acetivity_pingyu_success.setAction(context);
                //acetivity_pingyu_tovoid.setAction(context);

                acetivity_guihuan_lease.setAction(context); //事项-待办事项
                activity_yqdbsx.setAction(context);         //事项-逾期待办
                acetivity_guihuan_lease1.setAction(context);//事项-租借-归还
                break;


        }
    }

    @Override
    public void OnEorr() {

    }
}