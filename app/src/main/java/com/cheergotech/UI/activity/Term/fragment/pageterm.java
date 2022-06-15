package com.cheergotech.UI.activity.Term.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Term.activity_termo;
import com.cheergotech.UI.activity.Term.activity_termoper;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.MyDecoration;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.studentunReviewNumber;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Constants;
import com.cheergotech.widget.widgetimage16;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2517:10
 * Description
 */
public class pageterm extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.eroot)
    TextView eroot;
    boolean istrue;
    public studentunReviewNumber stu;
    widgetimage16 t2, t3;

    public boolean isIstrue() {
        return istrue;
    }

    public pageterm(int type, widgetimage16 t2, widgetimage16 t3) {
        this.type = type;
        this.t2 = t2;
        this.t3 = t3;
    }

    public static Fragment show(int type) {
        pageterm pageterm = new pageterm(type, null, null);
        Bundle args = new Bundle();
        args.putInt(Constants.type, type);
        pageterm.setArguments(args);
        return pageterm;

    }

    public void setIstrue(boolean istrue) {

        for (Object o : list) {
            studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) o;
            if (studentunrcom.getStatus() == 2) {
                studentunrcom.setIsboolean(istrue);
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Bundle arguments = getArguments();
        // this.type = arguments.getInt(Constants.type);
    }

    @Override
    protected int getview() {
        return R.layout.pageterm;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji_name2, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                //0：全部 1：已评 2:未评
                studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) list.get(position);
                List<Object> objects = new ArrayList<>();
                objects.add(studentunrcom);

                if (studentunrcom.getStatus() == 1) {
                    //1：已评
                    activity_termoper.setAction(context, Integer.parseInt(studentunrcom.getId()));

                    //activity_termo.setAction(activity, gson.toJson(objects));
                } else {
                    //2:未评
                    activity_termo.setAction(activity, gson.toJson(objects));
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
        initData();
    }

    public void OnLoad(int type) {
        if (type == 1) {
            totalPage = 0;
            list.clear();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } else {
            totalPage++;
        }

        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        Datamodule.getInstance().semester(msgconfig.getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void OnSuccess(Object object) {
                stu = (studentunReviewNumber) object;
                PageInfo pageInfo = (PageInfo) stu.getPageInfo();
                List<studentunrcom> listm = pageInfo.getList();
                list.addAll(listm);
                adapter.notifyDataSetChanged();
                t2.show(stu.getReviewNumber(), 1, 0);
                t3.show(stu.getUnReviewNumber(), 0, 0);
                OnEorr();
            }
        });
    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {
        if (eroot != null) {
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
