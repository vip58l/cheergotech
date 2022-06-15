package com.cheergotech.UI.activity.joblist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.submitstulist;
import com.cheergotech.UI.model.submitstulist2;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业信息-已提交作业列表
 */
public class activity_alljob extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    int id;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_alljob.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_alljob.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_alljob;
    }

    @Override
    public void iniview() {
        id = getIntent().getIntExtra(Constants.id, 0);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji_name3, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                //-1待完成 0已提交 1已批改 2驳回
                submitstulist2 submitstulist2 = (com.cheergotech.UI.model.submitstulist2) list.get(position);
                switch (submitstulist2.getStatus()) {
                    case -1:
                    case 0:
                        //作业详情--未批改
                        activity_job_correct.setAction(activity, submitstulist2.getId());
                        break;
                    case 1:
                    case 2:
                        //作业详情--已批改
                        activity_job_correct2.setAction(activity, submitstulist2.getId());
                        break;
                }
            }

            @Override
            public void OnClick(View view) {

            }
        }));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                OnLoad(1);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                OnLoad(2);
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (Object o : list) {
                    submitstulist2 submitstulist2 = (com.cheergotech.UI.model.submitstulist2) o;
                    submitstulist2.setIsboolean(b);
                }
                adapter.notifyDataSetChanged();
            }
        });
        initData();
    }

    public void OnLoad(int type) {
        if (type == 1) {
            totalPage = 0;
            list.clear();
            adapter.notifyDataSetChanged();
        } else {
            totalPage++;
        }
        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        Datamodule.getInstance().submitstulist(id, totalPage, 20, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess(Object object) {
                submitstulist sub = (submitstulist) object;
                List<submitstulist2> submitstulist2s = sub.getList();
                list.addAll(submitstulist2s);
                adapter.notifyDataSetChanged();
                if (submitstulist2s.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }
                OnEorr();
            }
        });

    }

    @Override
    @OnClick({R.id.send1})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                List<Object> mlist = new ArrayList<>();
                for (Object o : list) {
                    submitstulist2 submitstulist2 = (com.cheergotech.UI.model.submitstulist2) o;
                    if (submitstulist2.isIsboolean()) {

                        if (submitstulist2.getStatus() == 0) {
                            mlist.add(submitstulist2);
                        }

                    }

                }
                if (mlist.size() == 0) {
                    Toasts.toastMessage(getString(R.string.ta12));
                    return;
                }
                //批量修改作业
                acetivity_back.setAction(activity, gson.toJson(mlist));
                break;
        }
    }

    @Override
    public void OnEorr() {
        eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            OnLoad(1);
        }
    }
}