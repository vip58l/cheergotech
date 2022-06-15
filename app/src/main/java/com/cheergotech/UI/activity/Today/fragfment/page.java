package com.cheergotech.UI.activity.Today.fragfment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Today.activity_pingyu;
import com.cheergotech.UI.activity.Today.activity_pingyuper;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.MyDecoration;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.PageInfo;
import com.cheergotech.UI.model.studentunReviewNumber;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
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
 * Data 2022/5/1917:04
 * Description 今天评语
 */
public class page extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    widgetimage16 t2, t3, t4;
    public boolean istrue;

    public boolean isIstrue() {
        return istrue;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
        for (Object o : list) {
            studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) o;
            if (studentunrcom.getStatus() == 3) {
                studentunrcom.setIsboolean(istrue);
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public page(int type, widgetimage16 t2, widgetimage16 t3, widgetimage16 t4) {
        this.type = type;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    @Override
    protected int getview() {
        return R.layout.item_fragement;
    }

    @Override
    public void iniview() {
        //0：全部 1：已表扬 2:待改进 3：未评
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji_name, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                studentunrcom studentunrcom = (com.cheergotech.UI.model.studentunrcom) list.get(position);
                List<Object> objects = new ArrayList<>();
                objects.add(list.get(position));

                switch (studentunrcom.getStatus()) {
                    case 1:
                    case 2:
                        //已评语
                        activity_pingyuper.setAction(activity, Integer.parseInt(studentunrcom.getId()));
                        break;
                    default:

                        //今日评语
                        activity_pingyu.setAction(activity, gson.toJson(objects));
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
        //今天评语
        Datamodule.getInstance().stucomment(msgconfig.getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                try {

                    studentunReviewNumber st = (com.cheergotech.UI.model.studentunReviewNumber) object;
                    t2.show(st.getUnReviewNumber(), 1, 0);
                    t3.show(st.getPraisedNumber(), 1, 0);
                    t4.show(st.getNotPraiseNumber(), 1, 0);

                    PageInfo pageInfo = (PageInfo) st.getPageInfo();
                    List<studentunrcom> studentunrcom = (List<com.cheergotech.UI.model.studentunrcom>) pageInfo.getList();
                    list.addAll(studentunrcom);
                    adapter.notifyDataSetChanged();
                    if (studentunrcom.size() == 0 && totalPage > 1) {
                        totalPage--;
                        Toasts.toastMessage(getString(R.string.err));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
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
