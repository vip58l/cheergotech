package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.baijichu.activity_bjchu_per;
import com.cheergotech.UI.activity.viecode.activity_picbage;
import com.cheergotech.UI.dialog.DialogMessage8;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.UI.model.kcirclelist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.widgetimage5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 班级圈列表
 */
public class holder4 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.root)
    ImageView root;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.datetime)
    TextView datetime;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.widgetimage1)
    widgetimage5 widgetimage1;
    @BindView(R.id.widgetimage2)
    widgetimage5 widgetimage2;
    @BindView(R.id.imgbg)
    ImageView imgbg;
    Adapter adapter;

    public holder4(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item1, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        kcirclelist kcirclelist = (com.cheergotech.UI.model.kcirclelist) o;
        msg.setText(kcirclelist.getDescribes());
        name.setText(kcirclelist.getRealName());
        time.setText(Config.timestamp(String.valueOf(kcirclelist.getCreateTime()), ""));

        //19人觉得很赞
        widgetimage1.setDrawable(kcirclelist.getIsAttention() == 0 ? true : false);
        widgetimage1.setMsg(kcirclelist.getAttentionNumber() + context.getString(R.string.ll1) + "");


        //6条评论   贴子详情
        widgetimage2.setDrawable(true);
        widgetimage2.setMsg(kcirclelist.getFollowers() + context.getString(R.string.ll2) + "");
        widgetimage2.setOnClickListener(view -> activity_bjchu_per.setAction(context, kcirclelist.getId()));
        itemView.setOnClickListener(view -> activity_bjchu_per.setAction(context, kcirclelist.getId()));
        if (!TextUtils.isEmpty(kcirclelist.getHeadImg())) {
            ImageLoadHelper.glideShowCornerImageWithUrl(context, kcirclelist.getHeadImg(), image);
        } else {
            ImageLoadHelper.glideShowCornerImageWithUrl(context, R.mipmap.avasss, image);
        }

        recyclerview.setVisibility(View.GONE);
        root.setVisibility(View.GONE);

        if (true) {
            List<String> lelist = getkcirclelist(kcirclelist.getDetailsImg());
            if (lelist.size() > 0) {
                if (lelist.size() == 1) {
                    //只有一张图片
                    recyclerview.setVisibility(View.GONE);
                    ImageLoadHelper.glideShowCornerImageWithUrl(context, lelist.get(0), imgbg, 6);
                    imgbg.setVisibility(View.VISIBLE);
                    imgbg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            List<imglist> mSelectedImages = new ArrayList<>();
                            imglist image = new imglist();
                            image.setId(String.valueOf(0));
                            image.setBgpic(lelist.get(0));
                            image.setPic(lelist.get(0));
                            mSelectedImages.add(image);
                            activity_picbage.setAction(context, mSelectedImages, 0);
                        }
                    });
                } else {
                    //有多张图片
                    listshowgetrecyclerview(lelist);
                    imgbg.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);

                }
            } else {
                imgbg.setVisibility(View.GONE);
            }

        }


        //如果是本人的内容可以提示删除
        if (userInfo.getId() == kcirclelist.getUserId()) {
            root.setVisibility(View.VISIBLE);
            //删除回调
            root.setOnClickListener(v -> DialogMessage8.show(context, new CallBcak() {
                @Override
                public void Onfall() {

                }

                @Override
                public void setOnClickListener(int type) {
                    if (callBcak != null && type == 1) {
                        callBcak.delete(position);
                    }


                }
            }));
        }

        if (callBcak != null) {
            //点赞回调
            widgetimage1.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }

    }

    /**
     * 多张图片处理数组分割
     */
    public static List<String> getkcirclelist(String path) {
        List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(path)||path.equals("[]")) {
            return list;
        }

        String a = path.replace("[", "");
        String b = a.replace("]", "");
        String s1 = b.replace("\\/", "/");
        String[] split = s1.split(",");
        for (String s : Arrays.asList(split)) {
            list.add(s.replace("\"", "").trim());
        }
        return list;
    }

    /**
     * 多张图片处理显示
     *
     * @param lelist
     */
    public void listshowgetrecyclerview(List<String> lelist) {
        List<Object> list = new ArrayList<>();
        list.addAll(lelist);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                //图片预览
                List<imglist> mSelectedImages = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    imglist image = new imglist();
                    String path = (String) list.get(i);
                    image.setId(String.valueOf(i));
                    image.setBgpic(path);
                    image.setPic(path);
                    mSelectedImages.add(image);
                }
                //进入图片浏览
                activity_picbage.setAction(context, mSelectedImages, position);
            }
        }));
        imgbg.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);


    }

}
