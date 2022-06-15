package com.cheergotech.UI.activity.viecode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.pageadapter;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.cheergotech.widget.HackyViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图片浏览
 */
public class activity_picbage extends BaseActivity {
    private static final String TAG = activity_picbage.class.getName();
    @BindView(R.id.viewpager)
    HackyViewPager viewPager;
    @BindView(R.id.pages)
    TextView pages;
    @BindView(R.id.title)
    TextView title;
    pageadapter myviewadapter;
    List<View> list = new ArrayList<>();
    List<imglist> perimg;

    public static void setAction(Context context, List<imglist> perimg,int position) {
        Intent intent = new Intent(context, activity_picbage.class);
        intent.putExtra(Constants.JSON, (Serializable) perimg);
        intent.putExtra(Constants.position, position);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_viewpages;
    }

    @Override
    public void iniview() {
        int position = getIntent().getIntExtra(Constants.position, 0);
        perimg = (List<imglist>) getIntent().getSerializableExtra(Constants.JSON);

        viewPager.setAdapter(myviewadapter = new pageadapter(context, getListimg(perimg)));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                SetPage(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(position);
        SetPage(position);
        initData();
    }

    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
        }
    }

    @Override
    @OnClick({R.id.back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void OnEorr() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();

    }

    /**
     * 返回VIEW集合
     *
     * @param perimgList
     * @return
     */
    private List<View> getListimg(List<imglist> perimgList) {
        for (imglist perimg : perimgList) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.show_item_image, null);
            ImageView photo_view = inflate.findViewById(R.id.photo_view);
            ImageLoadHelper.glideShowImageWithUrl(context, perimg.getPic(), photo_view);
            ProgressBar progressBar = inflate.findViewById(R.id.progressBar);
            progressBar.setVisibility(GONE);
            list.add(inflate);
        }
        return list;
    }

    /**
     * page页码数
     *
     * @param position
     */
    private void SetPage(int position) {
        imglist perimg = this.perimg.get(position);
        if (perimg == null) {
            return;
        }
        title.setVisibility(!TextUtils.isEmpty(perimg.getTitle()) ? View.VISIBLE : GONE);
        title.setText(perimg.getTitle());
        pages.setText(String.format("%s/%s", position + 1, list.size()));
    }

}