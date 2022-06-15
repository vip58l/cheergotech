package com.steven.selectimage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.steven.selectimage.R;
import com.steven.selectimage.model.Image;
import com.steven.selectimage.utils.StatusBarUtil;
import com.steven.selectimage.widget.PreViewImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览图片相册
 */
public class PreviewImageActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TextView mCount;
    private List<Image> mSelectedImages;
    private int currentItem;

    public static void setAction(Context context, List<Image> mSelectedImages, int position) {
        Intent intent = new Intent(context, PreviewImageActivity.class);
        intent.putParcelableArrayListExtra("preview_images", (ArrayList<? extends Parcelable>) mSelectedImages);
        intent.putExtra("currentItem", position);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preview_image;
    }

    @Override
    protected void init() {
        mViewPager = findViewById(R.id.viewPager);
        mCount = findViewById(R.id.tv_count);
        StatusBarUtil.statusBarTranslucent(activity);
        mSelectedImages = getIntent().getParcelableArrayListExtra("preview_images"); //接收外页传的参数
        currentItem = getIntent().getIntExtra("currentItem", 0);         //接收外页传的参数
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mSelectedImages.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                PreViewImageView imageView = new PreViewImageView(container.getContext());
                Glide.with(context).load(mSelectedImages.get(position).getPath()).into(imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCount.setText(String.format("%s/%s", (position + 1), mSelectedImages.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(currentItem);
        currentItem++;
        mCount.setText(String.format("%s/%s", currentItem, mSelectedImages.size()));
        findViewById(R.id.tv_back).setOnClickListener(view -> finish());
    }

}
