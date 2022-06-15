package com.cheergotech.UI.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseDialog;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Today.activity_manage;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.RecycleViewDivider;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.conters;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Toasts;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评语管理
 */
public class DialogMessage11 extends BaseDialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.eroot)
    TextView eroot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    CallBcak callBcak;

    public CallBcak getCallBcak() {
        return callBcak;
    }

    public void setCallBcak(CallBcak callBcak) {
        this.callBcak = callBcak;
    }

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage11 dialogMessage = new DialogMessage11(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage11(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        this.recyclerview.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerview.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 2, context.getResources().getColor(R.color.color_white999)));
        this.recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.DialogMessage11, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                conters conters = (com.cheergotech.UI.model.conters) list.get(position);
                if (callBcak != null) {
                    callBcak.setMtext(conters.getContent());
                }
                dismiss();
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
        inidate();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

    }

    public void OnLoad(int type) {
        if (type == 1) {
            totalPage = 0;
            list.clear();
            adapter.notifyDataSetChanged();
        } else {
            totalPage++;
        }

        inidate();
    }


    private void inidate() {
        totalPage++;
        Datamodule.getInstance().phraselist(totalPage, 20, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                health health = (com.cheergotech.UI.model.healthy.health) object;
                List<conters> contersList = health.getList();
                list.addAll(contersList);
                adapter.notifyDataSetChanged();
                eorr();
            }

            @Override
            public void Onfall() {

            }
        });
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item11;
    }

    @Override
    @OnClick({R.id.sendchicke1, R.id.sendchicke2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.sendchicke1: {
                //添加评语
                DialogMessage14.show(context, context.getString(R.string.taa1), context.getString(R.string.taa3), context.getString(R.string.tass2), new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setMtext(String mtext) {
                        if (TextUtils.isEmpty(mtext.trim())) {
                            Toasts.toastMessage(context.getString(R.string.toast14));
                            return;
                        }
                        Datamodule.getInstance().commentphrase(1, mtext, new CallBcak() {
                            @Override
                            public void OnSuccess() {
                                Toasts.toastMessage(context.getString(R.string.subuess));
                                OnLoad(1);
                            }

                            @Override
                            public void Onfall() {

                            }
                        });
                    }
                });
            }
            break;
            case R.id.sendchicke2:
                //评语管理 编辑
            {
                activity_manage.setAction(context);
                dismiss();

            }

            break;

        }

    }

    private void eorr() {
        eroot.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
    }


}
