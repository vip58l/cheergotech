package com.cheergotech.UI.activity.Student.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Student.activity_notcollected;
import com.cheergotech.UI.activity.Student.activity_student;
import com.cheergotech.UI.activity.Student.activity_temper;
import com.cheergotech.UI.activity.Student.activity_temperature;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.healthy.StudentJiankang;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.UI.model.healthy.healthlist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.widgetimage16;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 学生信息-学生健康
 */
public class page2 extends BaseFragment {
    private static final String TAG = page2.class.getName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    activity_student activityStudent;
    widgetimage16 widgetimage16;

    @Override
    protected int getview() {
        return R.layout.page_fragment;
    }

    public page2(int type, widgetimage16 widgetimage16) {
        this.type = type;
        this.widgetimage16 = widgetimage16;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.t3, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int type) {
                healthlist healthlist = (com.cheergotech.UI.model.healthy.healthlist) list.get(type);
                switch (healthlist.getHealthStatus()) {
                    case 2:
                        //学生健康-详情-异常
                        activity_temperature.setAction(context, gson.toJson(healthlist));
                        break;
                    case 3:
                        //学生健康-详情-未采集
                        activity_notcollected.setAction(context, gson.toJson(healthlist));
//                        activity_temperature.setAction(context, gson.toJson(healthlist));
                        break;
                    case 1:
                        //学生健康-详情-正常
                        activity_temper.setAction(context, gson.toJson(healthlist));
                        break;
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
        initData();


//        w1.setTitle("正常");
//        w1.setMsg("");
//        w1.setTitle("正常");
//        w1.setMsg("");
//        w1.setTitle("正常");
//        w1.setMsg("");
//        w1.setTitle("正常");
//        w1.setMsg("");

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
        //学生健康
        Datamodule.getInstance().stuinfohealth(msgconfig.getClassId(), totalPage, 20, type, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                StudentJiankang studentJiankang = (StudentJiankang) object;
                health pageInfo = (health) studentJiankang.getPageInfo();
                List<healthlist> healthlist = pageInfo.getList();
                List<String> navigatepageNums = pageInfo.getNavigatepageNums();
                list.addAll(healthlist);
                adapter.notifyDataSetChanged();
                if (healthlist.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }
                OnEorr();
                switch (type) {
                    case 2:
                        //异常采集数据
                        if (widgetimage16 != null) {
                            widgetimage16.show(studentJiankang.getAbnormalNum(), 1);
                        }
                        break;
                    case 3:
                        //未采集数据
                        if (widgetimage16 != null) {
                            widgetimage16.show(studentJiankang.getUnCollectNum(), 0);
                        }
                        break;
                }
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
