package com.cheergotech.UI.activity.joblist.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.joblist.activity_josbper;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.classtask;
import com.cheergotech.UI.model.classtaskedit;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2619:07
 * Description
 */
public class pagejob extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.eroot)
    TextView eroot;

    public pagejob(int type) {
        this.type = type;
    }

    @Override
    protected int getview() {
        return R.layout.main_page_job;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.activity_Joblist, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                //作业详情
                classtask classtask2 = (com.cheergotech.UI.model.classtask) list.get(position);
                activity_josbper.setAction(context, gson.toJson(classtask2));

                //删除作业
                if (false) {
                    Datamodule.getInstance().classtaskcancel(classtask2.getId(), new CallBcak() {
                        @Override
                        public void OnSuccess() {
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                            Toasts.toastMessage("删除成功");
                        }

                        @Override
                        public void Onfall() {

                        }
                    });
                }

                //编辑作业
                if (false) {
                    classtaskedit classtaskedit = new classtaskedit();
                    classtaskedit.setId(classtask2.getId());
                    classtaskedit.setAutos("");
                    classtaskedit.setClassId(msgconfig.getClassId());
                    classtaskedit.setImages("");
                    classtaskedit.setDescribes("测试");
                    classtaskedit.setRemarks("测试");
                    classtaskedit.setFiles("");
                    classtaskedit.setTitles("测试");
                    classtaskedit.setVideos("");
                    Datamodule.getInstance().classtaskedit(gson.toJson(classtaskedit), new CallBcak() {
                        @Override
                        public void OnSuccess() {
                            Toasts.toastMessage("删除成功");
                        }

                        @Override
                        public void Onfall() {

                        }
                    });
                }

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
        if (totalPage==0){
            initData();
        }

    }

    @Override
    public void initData() {
        totalPage++;
        //作业信息-列表
        Datamodule.getInstance().classtasklist(Msgconfig.getInstance().getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                health s = (health) object;
                List<classtask> classtaskslist = s.getList();
                list.addAll(classtaskslist);
                adapter.notifyDataSetChanged();
                if (classtaskslist.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }
                OnEorr();

            }

            @Override
            public void Onfall() {

            }
        });
    }

    public void OnLoad(int type) {
        if (type == 1) {
            totalPage = 0;
            list.clear();
            if (adapter!=null){
                adapter.notifyDataSetChanged();
            }

        } else {
            totalPage++;
        }
        initData();
    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {
        if (eroot!=null){
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        }

    }

}
