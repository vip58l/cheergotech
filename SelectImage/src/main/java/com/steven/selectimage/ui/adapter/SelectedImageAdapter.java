package com.steven.selectimage.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.steven.selectimage.R;
import com.steven.selectimage.model.Image;
import com.steven.selectimage.widget.recyclerview.CommonRecycleAdapter;
import com.steven.selectimage.widget.recyclerview.CommonViewHolder;
import com.steven.selectimage.widget.recyclerview.OnAddPicturesListener;
import com.steven.selectimage.widget.recyclerview.OnItemClickListener;
import com.steven.selectimage.widget.recyclerview.OnItemClicklistener2;

import java.util.List;

/**
 * 适配器 传递给父类->抽像类
 */
public class SelectedImageAdapter extends CommonRecycleAdapter<Image> {
    private final Context mContext;
    private OnItemClickListener mItemClickListener;
    private OnAddPicturesListener listener;
    private OnItemClicklistener2 onItemClicklistener2;
    String TAG = "SelectedImageAdapter";

    public SelectedImageAdapter(Context context, List<Image> data) {
        super(context, data, R.layout.selected_image_item);
        this.mContext = context;
    }

    public SelectedImageAdapter(Context context, List<Image> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    protected void convert(CommonViewHolder holder, Image image, int position) {
        ImageView ivThum = holder.getView(R.id.iv_selected_image);
        ImageView ivAdd = holder.getView(R.id.iv_add);
        RelativeLayout del = holder.getView(R.id.del);
        ImageView delt = holder.getView(R.id.deltaRelative);

        if (TextUtils.isEmpty(image.getPath())) {
            //item为添加按钮
            ivThum.setVisibility(View.GONE);
            del.setVisibility(View.GONE);
            ivAdd.setVisibility(View.VISIBLE);
            if (listener != null) {
                ivAdd.setOnClickListener(v -> listener.onAdd());   //接口回调
            }
            if (onItemClicklistener2 != null) {
                ivAdd.setOnClickListener(v -> onItemClicklistener2.onAdd());   //接口回调
            }
        } else {
            //item为普通图片
            ivThum.setVisibility(View.VISIBLE);
            del.setVisibility(View.VISIBLE);
            ivAdd.setVisibility(View.GONE);

            if (mItemClickListener != null) {
                ivThum.setOnClickListener(v -> mItemClickListener.onItemClick(position));

            }
            if (onItemClicklistener2 != null) {
                ivThum.setOnClickListener(v -> onItemClicklistener2.onItemClick(position));
                delt.setOnClickListener(v -> onItemClicklistener2.delonItem(position));
            }
        }

        Glide.with(mContext).load(image.getPath()).into(ivThum);
    }

    /**
     * 传入监听添加更多图片
     *
     * @param listener
     */
    public void setListener(OnAddPicturesListener listener) {
        this.listener = listener;
    }

    /**
     * 传递监听点击事件
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnItemClicklistener2(OnItemClicklistener2 onItemClicklistener2) {
        this.onItemClicklistener2 = onItemClicklistener2;
    }
}