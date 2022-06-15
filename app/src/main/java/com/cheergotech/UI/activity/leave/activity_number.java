package com.cheergotech.UI.activity.leave;

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
 * 请假人数
 */
public class activity_number extends BaseActivity {
    private static final String TAG = "activity_number";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_number.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_number;
    }

    public void iniview()  {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.teacher, callBcak));

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
        Datamodule.getInstance().leavelist(msgconfig.getClassId(), totalPage, 20, new CallBcak() {
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


                //XX人请假中
                number.setText(String.valueOf(leavelist.getTotal()));

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
        if (eroot != null) {
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        }

    }

    /**
     * 监听回调点击事件
     */
    protected CallBcak callBcak = new CallBcak() {
        @Override
        public void OnClick(View view) {

        }

        @Override
        public void setOnClickListener(int position) {
            //转到个人详情页
            volist volist = (com.cheergotech.UI.model.volist) list.get(position);
            activity_Leave.setAction(context, volist.getId());
        }

        @Override
        public void setOnLongClickListener(int position) {

        }
    };
}