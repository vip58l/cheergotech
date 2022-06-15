package com.cheergotech.UI.activity.baijichu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.banjiqian;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 班级圈-消息
 */
public class activity_message extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.eroot)
    TextView eroot;

    @Override
    protected int getview() {
        return R.layout.activity_messeuss;
    }


    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_message.class);
        context.startActivity(intent);
    }

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_message.class);
        context.startActivityForResult(intent, Config.sussess);
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
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bjchu_message, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                setResult(Config.setResult); //回调上一页刷新消息
                banjiqian banjiqian = (com.cheergotech.UI.model.banjiqian) list.get(position);
                //设置已阅读
                Datamodule.getInstance().messageisview(banjiqian.getId(), new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void OnSuccess(Object object) {
                        banjiqian.setIsView(1);
                        adapter.notifyDataSetChanged();
                    }
                });
                //班级圈-帖子详情
                activity_bjchu_per.setAction(context, banjiqian.getCircleId());
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
        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        Datamodule.getInstance().messagemine(totalPage, 20, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess(Object object) {
                PageInfo pageInfo = (PageInfo) object;
                List<banjiqian> banjiqians = pageInfo.getList();
                list.addAll(banjiqians);
                adapter.notifyDataSetChanged();
                if (banjiqians.size() == 0 && totalPage > 1) {
                    Toasts.toastMessage(getString(R.string.err));
                }
                OnEorr();
            }
        });

    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {
        eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
    }
}