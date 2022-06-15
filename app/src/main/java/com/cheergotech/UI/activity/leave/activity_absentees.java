package com.cheergotech.UI.activity.leave;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.dialog.DialogMessage;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.absence;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 缺勤人数
 */
public class activity_absentees extends BaseActivity {
    private static final String TAG = activity_absentees.class.getName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_absentees.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_absentees;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.t2, callBcak));
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
        //缺勤人数
        Datamodule.getInstance().absencelist(msgconfig.getClassId(), totalPage, 20, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                health healt = (health) object;
                List<absence> absenceList = healt.getList();
                List<Integer> navigatepageNums = healt.getNavigatepageNums();
                list.addAll(absenceList);
                adapter.notifyDataSetChanged();
                if (absenceList.size() == 0 && totalPage > 1) {
                    totalPage--;
                    Toasts.toastMessage(getString(R.string.err));
                }
                total.setText(String.valueOf(healt.getTotal()));
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


    private CallBcak callBcak = new CallBcak() {
        @Override
        public void setOnClickListener(int position) {

        }

        @Override
        public void setOnClickListener1(int position) {

        }

        @Override
        public void setOnClickListener2(int position) {
            //查看位置
            absence absence = (com.cheergotech.UI.model.absence) list.get(position);
            activity_location.setAction(context, gson.toJson(absence));
        }

        @Override
        public void setOnClickListener3(int position) {
            //编辑在校状态
            absence absence = (com.cheergotech.UI.model.absence) list.get(position);
            DialogMessage.show(context, absence.getStudentName(), new CallBcak() {
                @Override
                public void setOnboolean(boolean b) {

                }

                @Override
                public void setOnClickListener(int thype) {
                    if (thype < 4) {
                        //type 1:在校 2:请假 3:缺勤
                        Datamodule.getInstance().handlestatus(absence.getId(), thype, new CallBcak() {
                            @Override
                            public void OnSuccess() {
                                Toasts.success(getString(R.string.suttok));
                                setResult(Config.setResult);
                                switch (thype) {
                                    case 1:
                                    case 2: {
                                        list.remove(position);
                                        adapter.notifyDataSetChanged();
                                        total.setText(String.valueOf(list.size()));
                                    }

                                    break;
                                    case 3:
                                        //Toasts.show();
                                        break;
                                    case 4:
                                        //Toasts.showeorr("取消成功");
                                        break;
                                }

                            }

                            @Override
                            public void Onfall() {

                            }
                        });

                    }
                }
            });
        }

        @Override
        public void setOnClickListener4(int position) {

        }
    };
}