package com.cheergotech.UI.activity.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.UI.activity.adapter.holder.holder1;
import com.cheergotech.UI.activity.adapter.holder.holder10;
import com.cheergotech.UI.activity.adapter.holder.holder11;
import com.cheergotech.UI.activity.adapter.holder.holder12;
import com.cheergotech.UI.activity.adapter.holder.holder14;
import com.cheergotech.UI.activity.adapter.holder.holder15;
import com.cheergotech.UI.activity.adapter.holder.holder2;
import com.cheergotech.UI.activity.adapter.holder.holder3;
import com.cheergotech.UI.activity.adapter.holder.holder4;
import com.cheergotech.UI.activity.adapter.holder.holder5;
import com.cheergotech.UI.activity.adapter.holder.holder6;
import com.cheergotech.UI.activity.adapter.holder.holder7;
import com.cheergotech.UI.activity.adapter.holder.holder8;
import com.cheergotech.UI.activity.adapter.holder.holder9;
import com.cheergotech.UI.activity.adapter.holder.teacher;
import com.cheergotech.UI.activity.adapter.holder.teacher2;
import com.cheergotech.listen.CallBcak;

import java.util.List;

public class Adapter extends RecyclerView.Adapter {


    private Context context;
    private List<Object> list;
    private int type;
    private CallBcak callBcak;
    public final static int teacher = 1000;
    public final static int t2 = 1001;
    public final static int t3 = 1003;
    public final static int to_page1 = 101;
    public final static int bus_location = 102;
    public final static int bus_baiji = 103;
    public final static int bus_Img = 104;
    public final static int bjchu_message = 105;
    public final static int bus_baiji_per = 106;
    public final static int bus_baiji_text = 107;
    public final static int bus_baiji_name = 108;
    public final static int activity_manage = 109;
    public final static int DialogMessage11 = 110;
    public final static int activity_Joblist = 111;
    public final static int activity_list = 112;
    public final static int bus_Img2 = 113;
    public final static int page_list = 114;
    public final static int bus_baiji_name2 = 115;
    public final static int bus_baiji_name3 = 116;


    public Adapter(Context context, List<Object> list, int type, CallBcak callBcak) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.callBcak = callBcak;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (type) {
            case teacher:
                //请假人数
                return new teacher(context, parent, viewType);
            case t2:
                //缺勤人数
                return new teacher2(context, parent, viewType);
            case to_page1:
                return new holder1(context, parent, viewType);
            case t3:
                //学生健康
                return new holder2(context, parent, viewType);
            case bus_location:
                return new holder3(context, parent, viewType);
            case bus_baiji:
                return new holder4(context, parent, viewType);
            case bus_Img:
                return new holder5(context, parent, viewType);
            case bjchu_message:
                return new holder6(context, parent, viewType);
            case bus_baiji_per:
                return new holder7(context, parent, viewType);
            case bus_baiji_text:
                return new holder8(context, parent, viewType);
            case bus_baiji_name:
            case bus_baiji_name2:
            case bus_baiji_name3:
                return new holder9(context, parent, viewType);
            case activity_manage:
                return new holder10(context, parent, viewType);
            case DialogMessage11:
                return new holder11(context, parent, viewType);
            case activity_Joblist://作业列表
                return new holder12(context, parent, viewType);
            case activity_list://请假等处理
                return new holder13(context, parent, viewType);
            case bus_Img2:
                return new holder14(context, parent, viewType);
            case page_list:
                return new holder15(context, parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object itemholder = list.get(position);
        switch (type) {
            case teacher:
                com.cheergotech.UI.activity.adapter.holder.teacher teacher = (com.cheergotech.UI.activity.adapter.holder.teacher) holder;
                teacher.bind(itemholder, position, callBcak);
                break;
            case t2:
                teacher2 teacher2 = (com.cheergotech.UI.activity.adapter.holder.teacher2) holder;
                teacher2.bind(itemholder, position, callBcak);
                break;
            case to_page1:
                holder1 holder1 = (com.cheergotech.UI.activity.adapter.holder.holder1) holder;
                holder1.bind(itemholder, position, callBcak);
                break;
            case t3:
                holder2 holder2 = (com.cheergotech.UI.activity.adapter.holder.holder2) holder;
                holder2.bind(itemholder, position, callBcak);
                break;
            case bus_location:
                holder3 holder3 = (com.cheergotech.UI.activity.adapter.holder.holder3) holder;
                holder3.bind(itemholder, position, callBcak);
                break;
            case bus_baiji:
                holder4 holder4 = (com.cheergotech.UI.activity.adapter.holder.holder4) holder;
                holder4.bind(itemholder, position, callBcak);
                break;
            case bus_Img:
                holder5 holder5 = (com.cheergotech.UI.activity.adapter.holder.holder5) holder;
                holder5.bind(itemholder, position, callBcak);
                break;
            case bjchu_message:
                holder6 holder6 = (com.cheergotech.UI.activity.adapter.holder.holder6) holder;
                holder6.bind(itemholder, position, callBcak);
                break;
            case bus_baiji_per:
                holder7 holder7 = (com.cheergotech.UI.activity.adapter.holder.holder7) holder;
                holder7.bind(itemholder, position, callBcak);
                break;
            case bus_baiji_text:
                holder8 holder8 = (com.cheergotech.UI.activity.adapter.holder.holder8) holder;
                holder8.bind(itemholder, position, callBcak);
                break;
            case bus_baiji_name: {
                holder9 holder9 = (com.cheergotech.UI.activity.adapter.holder.holder9) holder;
                holder9.bind(itemholder, position, callBcak);
            }
            break;
            case activity_manage:
                holder10 holder10 = (com.cheergotech.UI.activity.adapter.holder.holder10) holder;
                holder10.bind(itemholder, position, callBcak);
                break;
            case DialogMessage11:
                holder11 holder11 = (com.cheergotech.UI.activity.adapter.holder.holder11) holder;
                holder11.bind(itemholder, position, callBcak);
                break;
            case activity_Joblist:
                holder12 holder12 = (com.cheergotech.UI.activity.adapter.holder.holder12) holder;
                holder12.bind(itemholder, position, callBcak);
                break;
            case activity_list:
                holder13 holder13 = (com.cheergotech.UI.activity.adapter.holder13) holder;
                holder13.bind(itemholder, position, callBcak);
                break;
            case bus_Img2:
                holder14 holder14 = (com.cheergotech.UI.activity.adapter.holder.holder14) holder;
                holder14.bind(itemholder, position, callBcak);
                break;
            case page_list:
                holder15 holder15 = (com.cheergotech.UI.activity.adapter.holder.holder15) holder;
                holder15.bind(itemholder, position, callBcak);
                break;
            case bus_baiji_name2: {
                holder9 holder9 = (com.cheergotech.UI.activity.adapter.holder.holder9) holder;
                holder9.bind2(itemholder, position, callBcak);
                break;
            }

            case bus_baiji_name3: {
                holder9 baiji_name3 = (com.cheergotech.UI.activity.adapter.holder.holder9) holder;
                baiji_name3.bind3(itemholder, position, callBcak);
                break;
            }


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
