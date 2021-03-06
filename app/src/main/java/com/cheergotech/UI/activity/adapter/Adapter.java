package com.cheergotech.UI.activity.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.UI.activity.adapter.holder.holder1;
import com.cheergotech.UI.activity.adapter.holder.holder10;
import com.cheergotech.UI.activity.adapter.holder.holder11;
import com.cheergotech.UI.activity.adapter.holder.holder12;
import com.cheergotech.UI.activity.adapter.holder.holder125;
import com.cheergotech.UI.activity.adapter.holder.holder126;
import com.cheergotech.UI.activity.adapter.holder.holder127;
import com.cheergotech.UI.activity.adapter.holder.holder128;
import com.cheergotech.UI.activity.adapter.holder.holder129;
import com.cheergotech.UI.activity.adapter.holder.holder13;
import com.cheergotech.UI.activity.adapter.holder.holder130;
import com.cheergotech.UI.activity.adapter.holder.holder135;
import com.cheergotech.UI.activity.adapter.holder.holder136;
import com.cheergotech.UI.activity.adapter.holder.holder137;
import com.cheergotech.UI.activity.adapter.holder.holder139;
import com.cheergotech.UI.activity.adapter.holder.holder14;
import com.cheergotech.UI.activity.adapter.holder.holder141;
import com.cheergotech.UI.activity.adapter.holder.holder15;
import com.cheergotech.UI.activity.adapter.holder.holder16;
import com.cheergotech.UI.activity.adapter.holder.holder17;
import com.cheergotech.UI.activity.adapter.holder.holder18;
import com.cheergotech.UI.activity.adapter.holder.holder19;
import com.cheergotech.UI.activity.adapter.holder.holder2;
import com.cheergotech.UI.activity.adapter.holder.holder20;
import com.cheergotech.UI.activity.adapter.holder.holder21;
import com.cheergotech.UI.activity.adapter.holder.holder22;
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
    public final static int workbenchlist = 117;

    public final static int workbench_folder = 118;
    public final static int bus_Img3 = 119;
    public final static int Statistics = 120;
    public final static int chatright = 121;
    public final static int bus_baiji_name4 = 122;
    public final static int bus_baiji_name5 = 123;
    public final static int bus_baiji_name6 = 124;
    public final static int type125 = 125;
    public final static int type126 = 126;
    public final static int type127 = 127;
    public final static int type128 = 128;
    public final static int type129 = 129;
    public final static int type130 = 130;
    public final static int type131 = 131;
    public final static int type132 = 132;
    public final static int type133 = 133;
    public final static int type134 = 134;
    public final static int type135 = 135;
    public final static int type136 = 136;
    public final static int type137 = 137;
    public final static int type138 = 138;
    public final static int type139 = 139;
    public final static int type140 = 140;
    public final static int type141 = 141;
    public final static int type142 = 142;

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
                //????????????
                return new teacher(context, parent, viewType);
            case t2:
                //????????????
                return new teacher2(context, parent, viewType);
            case to_page1:
                return new holder1(context, parent, viewType);
            case t3:
                //????????????
                return new holder2(context, parent, viewType);
            case bus_location:
                //????????????
                return new holder3(context, parent, viewType);
            case bus_baiji:
                //????????????
                return new holder4(context, parent, viewType);
            case bus_Img:
                //????????????
                return new holder5(context, parent, viewType);
            case bjchu_message:
                //
                return new holder6(context, parent, viewType);
            case bus_baiji_per:
                return new holder7(context, parent, viewType);
            case bus_baiji_text:
                return new holder8(context, parent, viewType);
            case bus_baiji_name:  //
            case bus_baiji_name2: //????????????
            case bus_baiji_name3: //????????????
            case bus_baiji_name4: //????????????
                return new holder9(context, parent, viewType);
            case activity_manage:
                return new holder10(context, parent, viewType);
            case DialogMessage11:
                return new holder11(context, parent, viewType);
            case activity_Joblist://????????????
                return new holder12(context, parent, viewType);
            case activity_list://???????????????
                return new holder13(context, parent, viewType);
            case bus_Img2:
                return new holder14(context, parent, viewType);
            case page_list:
                return new holder15(context, parent, viewType);
            case workbenchlist:
                return new holder16(context, parent, viewType);
            case workbench_folder:
                return new holder17(context, parent, viewType);
            case bus_Img3:
            case type142:
                return new holder18(context, parent, viewType);
            case Statistics:
                return new holder19(context, parent, viewType);
            case chatright:
                //??????????????????
                return new holder20(context, parent, viewType);
            case bus_baiji_name5:
                return new holder21(context, parent, viewType);
            case bus_baiji_name6:
                return new holder22(context, parent, viewType);
            case type125:
            case type133:
                return new holder125(context, parent, viewType);
            case type126:
            case type134:
                return new holder126(context, parent, viewType);
            case type127:
                return new holder127(context, parent, viewType);
            case type128:
                return new holder128(context, parent, viewType);
            case type129:
                return new holder129(context, parent, viewType);
            case type130:
            case type131:
            case type132:
                return new holder130(context, parent, viewType);
            case type135:
                return new holder135(context, parent, viewType);
            case type136:
            case type138:
            case type140:
                return new holder136(context, parent, viewType);
            case type137:
                return new holder137(context, parent, viewType);
            case type139:
                return new holder139(context, parent, viewType);
            case type141:
                return new holder141(context, parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object itemholder = list.get(position);
        switch (type) {
            case teacher: {
                com.cheergotech.UI.activity.adapter.holder.teacher teacher = (com.cheergotech.UI.activity.adapter.holder.teacher) holder;
                teacher.bind(itemholder, position, callBcak);
                break;
            }
            case t2: {
                teacher2 teacher2 = (com.cheergotech.UI.activity.adapter.holder.teacher2) holder;
                teacher2.bind(itemholder, position, callBcak);
                break;
            }
            case to_page1: {
                holder1 holder1 = (com.cheergotech.UI.activity.adapter.holder.holder1) holder;
                holder1.bind(itemholder, position, callBcak);
                break;
            }
            case t3: {
                holder2 holder2 = (com.cheergotech.UI.activity.adapter.holder.holder2) holder;
                holder2.bind(itemholder, position, callBcak);
                break;
            }
            case bus_location: {
                holder3 holder3 = (com.cheergotech.UI.activity.adapter.holder.holder3) holder;
                holder3.bind(itemholder, position, callBcak);
                break;
            }
            case bus_baiji: {
                holder4 holder4 = (com.cheergotech.UI.activity.adapter.holder.holder4) holder;
                holder4.bind(itemholder, position, callBcak);
                break;
            }
            case bus_Img: {
                holder5 holder5 = (com.cheergotech.UI.activity.adapter.holder.holder5) holder;
                holder5.bind(itemholder, position, callBcak);
                break;
            }
            case bjchu_message: {
                holder6 holder6 = (com.cheergotech.UI.activity.adapter.holder.holder6) holder;
                holder6.bind(itemholder, position, callBcak);
                break;
            }
            case bus_baiji_per: {
                holder7 holder7 = (com.cheergotech.UI.activity.adapter.holder.holder7) holder;
                holder7.bind(itemholder, position, callBcak);
                break;
            }
            case bus_baiji_text: {
                holder8 holder8 = (com.cheergotech.UI.activity.adapter.holder.holder8) holder;
                holder8.bind(itemholder, position, callBcak);
                break;
            }
            case bus_baiji_name: {
                holder9 holder9 = (com.cheergotech.UI.activity.adapter.holder.holder9) holder;
                holder9.bind(itemholder, position, callBcak);
                break;
            }
            case activity_manage: {
                holder10 holder10 = (com.cheergotech.UI.activity.adapter.holder.holder10) holder;
                holder10.bind(itemholder, position, callBcak);
                break;
            }
            case DialogMessage11: {
                holder11 holder11 = (com.cheergotech.UI.activity.adapter.holder.holder11) holder;
                holder11.bind(itemholder, position, callBcak);
                break;
            }
            case activity_Joblist: {
                holder12 holder12 = (com.cheergotech.UI.activity.adapter.holder.holder12) holder;
                holder12.bind(itemholder, position, callBcak);
                break;
            }
            case activity_list: {
                holder13 holder13 = (com.cheergotech.UI.activity.adapter.holder.holder13) holder;
                holder13.bind(itemholder, position, callBcak);
                break;
            }
            case bus_Img2: {
                holder14 holder14 = (com.cheergotech.UI.activity.adapter.holder.holder14) holder;
                holder14.bind(itemholder, position, callBcak);
                break;
            }
            case page_list: {
                holder15 holder15 = (com.cheergotech.UI.activity.adapter.holder.holder15) holder;
                holder15.bind(itemholder, position, callBcak);
                break;
            }
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
            case workbenchlist: {
                ((holder16) holder).bind(itemholder, position, callBcak);
                break;
            }
            case workbench_folder: {
                ((holder17) holder).bind(itemholder, position, callBcak);
                break;
            }
            case bus_Img3: {
                holder18 holder18 = (com.cheergotech.UI.activity.adapter.holder.holder18) holder;
                holder18.bind(itemholder, position, callBcak);
                break;
            }
            case Statistics: {
                holder19 holder19 = (holder19) holder;
                holder19.bind(itemholder, position, callBcak);
                break;
            }
            case chatright: {
                holder20 holder20 = (holder20) holder;
                holder20.bind(itemholder, position, callBcak);
                break;
            }
            case bus_baiji_name4: {
                ((holder9) holder).bind4(itemholder, position, callBcak);
                break;
            }
            case bus_baiji_name5: {
                ((holder21) holder).bind(itemholder, position, callBcak);
                break;

            }
            case bus_baiji_name6: {
                ((holder22) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type125: {
                ((holder125) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type126: {
                ((holder126) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type127: {
                ((holder127) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type128: {
                ((holder128) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type129: {
                ((holder129) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type130: {
                ((holder130) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type131: {
                ((holder130) holder).bind2(itemholder, position, callBcak);
                break;
            }
            case type132: {
                ((holder130) holder).bind3(itemholder, position, callBcak);
                break;
            }
            case type133: {
                ((holder125) holder).bind2(itemholder, position, callBcak);
                break;
            }
            case type134: {
                ((holder126) holder).bind2(itemholder, position, callBcak);
                break;
            }
            case type135: {
                ((holder135) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type136: {
                ((holder136) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type137: {
                ((holder137) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type138: {
                ((holder136) holder).bind2(itemholder, position, callBcak);
                break;
            }
            case type139: {
                ((holder139) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type140: {
                ((holder136) holder).bind3(itemholder, position, callBcak);
                break;
            }
            case type141: {
                ((holder141) holder).bind(itemholder, position, callBcak);
                break;
            }
            case type142: {
                ((holder18) holder).bind2(itemholder, position, callBcak);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
