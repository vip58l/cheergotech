package com.cheergotech.UI.activity.baijichu;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.viecode.activity_picbage;
import com.cheergotech.UI.dialog.DialogMessage6;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.followerInfo;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.UI.model.itembjqper;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.widgetimage5;
import com.cheergotech.widget.widgetimage7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 班级圈-帖子详情
 */
public class activity_bjchu_per extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.datetime)
    TextView datetime;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.root)
    ImageView root;
    @BindView(R.id.imgbg)
    ImageView imgbg;
    @BindView(R.id.widgetimage1)
    widgetimage5 w1;
    @BindView(R.id.widgetimage2)
    widgetimage5 w2;
    @BindView(R.id.widgetimage7)
    widgetimage7 w7;
    itembjqper item;
    Adapter adapter1;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_bjchu_per.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_bjchu_per.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_bjchu_per;
    }

    @Override
    public void iniview() {
        root.setVisibility(View.GONE);
        recyclerview2.setLayoutManager(new LinearLayoutManager(context));
        recyclerview2.setAdapter(adapter = new Adapter(context, list, Adapter.bus_baiji_per, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {

            }

            @Override
            public void delete(int position) {
                //删除留言
                followerInfo followerInfo = (com.cheergotech.UI.model.followerInfo) list.get(position);
                Datamodule.getInstance().followerdelete(followerInfo.getId(), new CallBcak() {
                    @Override
                    public void OnSuccess() {

                        Toasts.toastMessage(getString(R.string.toaswt));
                        list.remove(followerInfo);
                        adapter.notifyDataSetChanged();
                        w7.setTitle(String.format("%s 条留言", list.size()));

                    }

                    @Override
                    public void Onfall() {

                    }
                });

            }
        }));
        initData();

    }

    @Override
    public void initData() {
        int id = getIntent().getIntExtra(Constants.id, 0);
        Datamodule.getInstance().classcircleinfo(id, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                item = (com.cheergotech.UI.model.itembjqper) object;
                if (TextUtils.isEmpty(item.getHeadImg())) {
                    ImageLoadHelper.glideShowImageWithUrl(context, R.mipmap.avasss, image);
                } else {
                    ImageLoadHelper.glideShowImageWithUrl(context, item.getHeadImg(), image);
                }
                name.setText(item.getRealName());
                msg.setText(item.getDescribes());
                time.setText(Config.timestamp(String.valueOf(item.getCreateTime()), null));

                String detailsImg = item.getDetailsImg();
                detailsImg = detailsImg.replace("\"", "");
                detailsImg = detailsImg.replace("[", "");
                detailsImg = detailsImg.replace("]", "");
                detailsImg = detailsImg.replace("\\/", "/").trim();
                String[] split = detailsImg.split(",");
                List<String> listm = Arrays.asList(split);
                List<Object> listm2 = new ArrayList<>();
                listm2.addAll(listm);

                //图片九宫格
                recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
                recyclerview.setAdapter(adapter1 = new Adapter(context, listm2, Adapter.bus_Img, new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int position) {
                        //图片预览
                        List<imglist> mSelectedImages = new ArrayList<>();
                        for (int i = 0; i < listm2.size(); i++) {
                            imglist image = new imglist();
                            String path = (String) listm2.get(i);
                            image.setId(String.valueOf(i));
                            image.setBgpic(path);
                            image.setPic(path);
                            mSelectedImages.add(image);
                        }
                        //进入图片浏览
                        activity_picbage.setAction(context, mSelectedImages, position);

                    }

                }));
                if (!TextUtils.isEmpty(detailsImg)) {
                    recyclerview.setVisibility(View.VISIBLE);
                }

                w1.setMsg(String.format(context.getString(R.string.per1), item.getAttentionNumber() + ""));
                w1.setDrawable(item.getIsAttention() == 0 ? true : false);

                w1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int iAttentio = item.getIsAttention() == 0 ? 1 : 0;
                        Datamodule.getInstance().attention(item.getId(), iAttentio, new CallBcak() {
                            @Override
                            public void OnSuccess() {
                                if (iAttentio == 1) {
                                    //添加1人点赞
                                    item.setAttentionNumber(item.getAttentionNumber() + 1);
                                } else {
                                    //点击人数大于0 减少1人点赞
                                    if (item.getAttentionNumber() > 0) {
                                        item.setAttentionNumber(item.getAttentionNumber() - 1);
                                    }
                                }
                                item.setIsAttention(iAttentio);
                                w1.setDrawable(item.getIsAttention() == 0 ? true : false);
                                w1.setMsg(String.format(context.getString(R.string.per1), item.getAttentionNumber() + ""));
                                Toasts.showShort(getString(iAttentio == 1 ? R.string.d1 : R.string.d2));
                            }

                            @Override
                            public void Onfall() {

                            }
                        });

                    }
                });

                w2.setMsg(String.format(getString(R.string.itetmsg) + "", item.getNumbers()));
                w2.setDrawable(context.getResources().getDrawable(R.mipmap.showlook));
                w7.setTitle(String.format("%s 条留言", item.getFollowers()));
                List<followerInfo> Info = item.getFollowerInfo();
                list.addAll(Info);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fall() {

            }
        });
    }

    @Override
    @OnClick({R.id.barrier})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.barrier:
                DialogMessage6.show(context, getString(R.string.barrier2), null, null, new CallBcak() {
                    @Override
                    public void setMtext(String mtext) {
                        if (TextUtils.isEmpty(mtext)) {
                            Toasts.toastMessage(getString(R.string.mtext2));
                            return;
                        }

                        //发送评论
                        Datamodule.getInstance().comments(item.getId(), mtext, this);

                    }

                    @Override
                    public void OnSuccess() {
                        list.clear();
                        adapter.notifyDataSetChanged();
                        initData();
                        Toasts.showShort(getString(R.string.toast2));
                    }
                });
                break;
        }

    }

    @Override
    public void OnEorr() {

    }
}