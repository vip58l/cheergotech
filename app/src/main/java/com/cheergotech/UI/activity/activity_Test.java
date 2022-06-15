package com.cheergotech.UI.activity;

import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 新增优化测试文件
 * 使用不到可以删除
 */
public class activity_Test extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @Override
    protected int getview() {
        return R.layout.activity_test;
    }

    @Override
    public void iniview() {
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