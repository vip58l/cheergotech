package com.cheergotech.UI.activity.workbench.folder.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.MyDecoration;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.dialog.DialogMessage16;
import com.cheergotech.listen.CallBcak;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1310:51
 * Description
 */
public class fragment_folder extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    public fragment_folder(int type) {
        this.type = type;
    }

    @Override
    protected int getview() {
        return R.layout.item_fragement;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.workbench_folder, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {


                //删除文件
                DialogMessage10.show(context, "删除文件", "确定要删除已选择的文件吗？", "取消", "删除", new CallBcak() {
                    @Override
                    public void fall() {

                    }

                    @Override
                    public void setOnClickListener(int position) {
                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                break;
                        }

                    }
                });
            }

            @Override
            public void OnClick(View view) {

            }
        }));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败

            }
        });
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
}
