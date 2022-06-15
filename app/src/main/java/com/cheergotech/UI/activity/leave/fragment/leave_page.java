package com.cheergotech.UI.activity.leave.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.leave.activity_Leave2;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.leavelist;
import com.cheergotech.UI.model.volist;
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
 * Data 2022/5/2311:22
 * Description
 */
public class leave_page extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    public leave_page(int type) {
        this.type = type;
    }

    @Override
    protected int getview() {
        return R.layout.keave_list;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.activity_list, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                //请假信息-详情
                volist volist = (com.cheergotech.UI.model.volist) list.get(position);
                activity_Leave2.setAction(activity, volist.getId());

            }

            @Override
            public void Onfall() {

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
            adapter.notifyDataSetChanged();
        } else {
            totalPage++;
        }

        initData();
    }

    @Override
    public void initData() {
      totalPage++;
        //查询类型 0：全部 1：待审批 2：已审批 请假列表(new)
        Datamodule.getInstance().leavelistnew(msgconfig.getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                leavelist leavelist = (leavelist) object;
                List<volist> mlist = (List<volist>) leavelist.getList();
                List<String> navigatepageNums = (List<String>) leavelist.getNavigatepageNums();
                list.addAll(mlist);
                adapter.notifyDataSetChanged();

                if (mlist.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }

                OnEorr();
            }

            @Override
            public void Onfall() {
                OnEorr();
            }
        });
    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

            if (eroot!=null){
                eroot.setVisibility(list.size()==0?View.VISIBLE:View.GONE);
            }

    }
}
