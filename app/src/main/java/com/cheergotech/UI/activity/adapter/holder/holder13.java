package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.volist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.widgetimage19;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/2311:28
 * Description
 */
public class holder13 extends BaseHolder {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.className)
    widgetimage19 className;
    @BindView(R.id.time)
    widgetimage19 time;
    @BindView(R.id.titles)
    widgetimage19 titles;
    @BindView(R.id.describes)
    widgetimage19 describes;
    @BindView(R.id.status)
    TextView status;

    public holder13(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_count4, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;


        volist volist = (com.cheergotech.UI.model.volist) o;
        name.setText(volist.getStudentName() + context.getString(R.string.toasth5) + "");
        createTime.setText(TextUtils.isEmpty(String.valueOf(volist.getCreateTime())) ? "无" : Config.stampToDate(String.valueOf(volist.getCreateTime()), null) + " 提交");
        className.setMsg(TextUtils.isEmpty(volist.getClassName()) ? "无" : volist.getClassName());
        time.setMsg(String.format(context.getString(R.string.toasth4) + "", Config.stampToDateh(String.valueOf(volist.getStartTime()), null), Config.stampToDateh(String.valueOf(volist.getOverTime()), null), volist.getDays()));
        titles.setMsg(volist.getTitles());
        describes.setMsg(volist.getDescribes());

        //审批状态 0审批中 1已审批 2已驳回
        switch (volist.getStatus()) {
            case 0:
                status.setTextColor(context.getResources().getColor(R.color.shapa));
                status.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.e111));
                status.setText(R.string.toasth1);
                break;
            case 1:
                status.setTextColor(context.getResources().getColor(R.color.ff0dc900));
                status.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.e222));
                status.setText(R.string.toasth2);
                break;
            case 2:
                status.setTextColor(context.getResources().getColor(R.color.shape));
                status.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.e333));
                status.setText(R.string.toasth3);
                break;
            default:
                break;

        }
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }

    }


}
