package com.cheergotech.UI.activity.Today;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.RecycleViewDivider;
import com.cheergotech.UI.dialog.DialogMessage14;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.conters;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 今日评语-发布评语-评语管理 编辑
 */
public class activity_manage extends BaseActivity {
    String TAG = "activity_manage";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    String msg = "";

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_manage.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_manage;
    }

    @Override
    public void iniview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 5, context.getResources().getColor(R.color.color_white999)));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.activity_manage, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {

            }

            @Override
            public void setOnClickListener1(int position) {
                conters conters = (com.cheergotech.UI.model.conters) list.get(position);
                DialogMessage14.show(context, getString(R.string.e1), getString(R.string.e2), getString(R.string.e5), conters.getContent(), new CallBcak() {
                    @Override
                    public void setMtext(String mtext) {
                        if (TextUtils.isEmpty(mtext)) {
                            Toasts.toastMessage(getString(R.string.edite1));
                            return;
                        }
                        msg = mtext;
                        Datamodule.getInstance().commentphrasedit(conters.getId(), mtext, this);
                    }

                    @Override
                    public void OnSuccess() {
                        Toasts.toastMessage(getString(R.string.toask1));
                        conters.setContent(msg);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void Onfall() {
                        Toasts.toastMessage(getString(R.string.toas2));
                    }
                });

            }

            @Override
            public void OnClick(View view) {

            }
        }));
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (Object o : list) {
                    conters conters = (com.cheergotech.UI.model.conters) o;
                    conters.setIs(b);
                }
                adapter.notifyDataSetChanged();

            }
        });
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
            checkbox.setChecked(false);
        } else {
            totalPage++;
        }

        initData();
    }

    @Override
    public void initData() {
        totalPage++;
        Datamodule.getInstance().phraselist(totalPage, 20, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                health health = (com.cheergotech.UI.model.healthy.health) object;
                List<conters> contersList = health.getList();
                list.addAll(contersList);
                adapter.notifyDataSetChanged();
                if (totalPage > 1 && contersList.size() == 0) {
                    Toasts.showShort(getString(R.string.err));
                }
                OnEorr();
            }

            @Override
            public void Onfall() {

            }
        });
    }

    @Override
    @OnClick({R.id.del})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.del:
                StringBuffer sb = new StringBuffer();
                List<conters> del = new ArrayList<>();

                for (Object o : list) {
                    conters conters = (com.cheergotech.UI.model.conters) o;
                    if (conters.isIs()) {
                        sb.append(String.valueOf(conters.getId()) + ",");
                        del.add(conters);
                    }
                }


                if (del.size() == 0) {
                    Toasts.toastMessage(getString(R.string.toast8));
                    return;
                }

                //批量删除数据
                Datamodule.getInstance().commentphrase(sb.toString(), new CallBcak() {
                    @Override
                    public void OnSuccess() {
                        for (conters conters : del) {
                            if (conters.isIs()) {
                                list.remove(conters);
                            }

                        }
                        adapter.notifyDataSetChanged();
                        Toasts.toastMessage("删除成功");
                    }

                    @Override
                    public void Onfall() {

                    }
                });
                break;
        }


    }

    @Override
    public void OnEorr() {

        if (eroot != null)
            eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);

    }
}