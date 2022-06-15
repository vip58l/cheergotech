package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.classtask;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;

/**
 * 作业列表
 */
public class holder12 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.datetime)
    TextView datetime;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.corrected)
    TextView corrected;
    @BindView(R.id.imgage)
    ImageView imgage;

    public holder12(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_zhuoyei_holder, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        classtask c = (classtask) o;
        title.setText(c.getTitles());
        datetime.setText(String.format(context.getString(R.string.h1) + "", Config.stampToDate(String.valueOf(c.getCreateTime()), null)));
        content.setText(c.getDescribes());
        submit.setText(Html.fromHtml(String.format(context.getString(R.string.h2) + "", c.getSubmitNum() + "")));
        corrected.setText(Html.fromHtml(String.format(context.getString(R.string.h3) + "", c.getCheckNum(), c.getAllNum())));

        Log.d("TAG", "bind: " + c.toString());
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }

    }

}
