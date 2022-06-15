package com.cheergotech.UI.activity.toschool.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Devrlist;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.normalresult;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 到校人数-列表
 * 类型 0：全部 2：正常 4：迟到
 */
public class page1 extends BaseFragment {
    private static final String TAG = page1.class.getName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    TextView total, allNum;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    public normalresult normalresult;

    public page1(int type, TextView total, TextView allNum) {
        this.type = type; //类型 0：全部 2：正常 4：迟到
        this.total = total;
        this.allNum = allNum;
    }

    @Override
    protected int getview() {
        return R.layout.page1;
    }

    @Override
    public void iniview() {
        adapter = new Adapter(context, list, Adapter.to_page1, null);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter);
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
            adapter.notifyDataSetChanged();
        } else {
            totalPage++;
        }

        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        //到校人数-列表
        Datamodule.getInstance().normallist(msgconfig.getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                normalresult = (com.cheergotech.UI.model.normalresult) object;
                PageInfo pageInfo = (PageInfo) normalresult.getPageInfo();
                List<Devrlist> devrlist = (List<Devrlist>) pageInfo.getList();
                list.addAll(devrlist);
                adapter.notifyDataSetChanged();
                if (type != 4) {
                    total.setText(String.valueOf(normalresult.getNormalNum()));     //正常到校
                    allNum.setText("/" + String.valueOf(normalresult.getAllNum())); //全部学生
                }
                if (devrlist.size() == 0 && totalPage > 1) {
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
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {
        if (eroot != null) {
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        }

    }

}
