package com.cheergotech.UI.activity.baijichu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.classcircle;
import com.cheergotech.UI.model.kcirclelist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.widgetimage18;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 班级圈列表
 */
public class activity_bjchu extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.message)
    widgetimage18 message;
    @BindView(R.id.lineboot)
    LinearLayout lineboot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rounded)
    TextView rounded;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_bjchu.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_bjchu;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                //点赞处理
                kcirclelist kcirclelist = (com.cheergotech.UI.model.kcirclelist) list.get(position);
                int iAttentio = kcirclelist.getIsAttention() == 1 ? 0 : 1;
                Datamodule.getInstance().attention(kcirclelist.getId(), iAttentio, new CallBcak() {
                    @Override
                    public void OnSuccess() {
                        if (iAttentio == 1) {
                            //添加1人点赞
                            kcirclelist.setAttentionNumber(kcirclelist.getAttentionNumber() + 1);
                        } else {
                            //点击人数大于0 减少1人点赞
                            if (kcirclelist.getAttentionNumber() > 0) {
                                kcirclelist.setAttentionNumber(kcirclelist.getAttentionNumber() - 1);
                            }
                        }

                        kcirclelist.setIsAttention(iAttentio);
                        adapter.notifyDataSetChanged();
                        Toasts.showShort(getString(iAttentio == 1 ? R.string.d1 : R.string.d2));
                    }

                    @Override
                    public void Onfall() {

                    }
                });
            }

            @Override
            public void delete(int position) {
                kcirclelist kcirclelist = (com.cheergotech.UI.model.kcirclelist) list.get(position);
                Datamodule.getInstance().circledelete(kcirclelist.getId(), new CallBcak() {
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
        //班级圈
        Datamodule.getInstance().classcircle(Msgconfig.getInstance().getClassId(), totalPage, 20, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                classcircle cls = (com.cheergotech.UI.model.classcircle) object;
                PageInfo pageInfo = (PageInfo) cls.getPageInfo();
                List<kcirclelist> kcirclelist = (List<com.cheergotech.UI.model.kcirclelist>) pageInfo.getList();
                list.addAll(kcirclelist);
                adapter.notifyDataSetChanged();
                message.setRounded(cls.getNotViewNumber());
                if (cls.getNotViewNumber() > 0) {
                    rounded.setText(String.valueOf(cls.getNotViewNumber()));
                    rounded.setVisibility(View.VISIBLE);
                } else {
                    rounded.setVisibility(View.GONE);
                }
                if (kcirclelist.size() == 0 && totalPage > 1) {
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

    @Override
    @OnClick({R.id.Post, R.id.message, R.id.all, R.id.add})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.Post:
                //发贴
            {
                lineboot.setVisibility(View.GONE);
                activity_ress.setAction(activity);
            }

            break;
            case R.id.message:
                //消息
            {
                lineboot.setVisibility(View.GONE);
                activity_message.setAction(activity);
            }
            break;
            case R.id.all:
                //我的
            {
                lineboot.setVisibility(View.GONE);
                activity_mybjchu.setAction(context);
            }

            break;
            case R.id.add:
                //显示更多
                int visibility = lineboot.getVisibility();
                lineboot.setVisibility(visibility == 0 ? View.GONE : View.VISIBLE);
                break;
        }

    }

    @Override
    public void OnEorr() {
        if (eroot != null) {
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            mRefreshLayout.autoRefresh();
        }

    }


}