package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.studentunrcom;
import com.cheergotech.UI.model.submitstulist2;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder9 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.istrue)
    TextView istrue;
    @BindView(R.id.imgage)
    ImageView imgage;

    public holder9(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_name, parent, false));
    }

    @Override
    //今日评语
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        studentunrcom s = (studentunrcom) o;
        title.setText(s.getStudentName());
        msg.setText(s.getStatusName());
        checkbox.setChecked(s.isIsboolean());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                s.setIsboolean(b);
            }
        });

        istrue.setVisibility(View.GONE);
        msg.setBackground(context.getDrawable(R.drawable.itemdrbac));

        //0：全部 1：已表扬 2:待改进 3：未评
        switch (s.getStatus()) {
            case 0:
            case 3:
                msg.setVisibility(View.GONE);
                imgage.setVisibility(View.GONE);
                break;
            case 1:
                msg.setVisibility(View.VISIBLE);
                imgage.setVisibility(View.VISIBLE);
                istrue.setVisibility(View.VISIBLE);
                msg.setBackground(context.getDrawable(R.drawable.itemdrbac));
                break;
            case 2:
                msg.setVisibility(View.VISIBLE);
                imgage.setVisibility(View.VISIBLE);
                istrue.setVisibility(View.VISIBLE);
                msg.setBackground(context.getDrawable(R.drawable.ite5_4));
                break;
        }
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }


    }

    /**
     * 学期评语
     *
     * @param o
     * @param position
     * @param callBcak
     */
    public void bind2(Object o, int position, CallBcak callBcak) {
        this.object = o;
        studentunrcom s = (studentunrcom) o;
        title.setText(s.getStudentName());
        msg.setText(s.getStatusName());
        checkbox.setChecked(s.isIsboolean());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkbox.setChecked(b);
                s.setIsboolean(b);
            }
        });

        istrue.setVisibility(View.GONE);
        msg.setBackground(context.getDrawable(R.drawable.itemdrbac));

        //0：全部 1：已评 2:未评
        if (s.getStatus() == 1) {
            checkbox.setEnabled(false);
            istrue.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            imgage.setVisibility(View.VISIBLE);
            msg.setBackground(context.getDrawable(R.drawable.itemdrbac));
        } else {
            msg.setVisibility(View.GONE);
            imgage.setVisibility(View.GONE);
            msg.setBackground(context.getDrawable(R.drawable.ite5_4));
        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }


    }

    /**
     * 作业批改
     *
     * @param o
     * @param position
     * @param callBcak
     */
    public void bind3(Object o, int position, CallBcak callBcak) {
        this.object = o;
        submitstulist2 s = (submitstulist2) o;
        title.setText(s.getStudentName());
        msg.setText(s.getStatusName());
        checkbox.setChecked(s.isIsboolean());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkbox.setChecked(b);
                s.setIsboolean(b);
            }
        });

        //作业提交状态 -1待完成 0已提交 1已批改 2驳回
        istrue.setVisibility(View.GONE);
        msg.setBackground(context.getDrawable(R.drawable.itemdrbac));

        //作业提交状态 -1待完成 0已提交 1已批改 2驳回
        if (s.getStatus() == 1 || s.getStatus() == 2) {
            checkbox.setEnabled(false);
            istrue.setVisibility(View.VISIBLE);
            msg.setBackground(context.getDrawable(R.drawable.ite5_4));
        } else {
            msg.setBackground(context.getDrawable(R.drawable.itemdrbac));
        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }


    }

    /**
     * 选择群组
     *
     * @param o
     * @param position
     * @param callBcak
     */
    public void bind4(Object o, int position, CallBcak callBcak) {
        this.object = o;
        msg.setVisibility(View.GONE);
        imgage.setVisibility(View.GONE);

        title.setText("111111");
        istrue.setVisibility(View.GONE);

        if (position > 5) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkbox.setChecked(b);
            }
        });
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }
    }
}
