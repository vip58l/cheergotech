package com.cheergotech.UI.activity.viecode;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.subadvice;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Toasts;
import com.steven.selectimage.model.Image;
import com.steven.selectimage.ui.PreviewImageActivity;
import com.steven.selectimage.ui.SelectImageActivity;
import com.steven.selectimage.ui.adapter.SelectedImageAdapter;
import com.steven.selectimage.utils.TDevice;
import com.steven.selectimage.widget.recyclerview.OnItemClicklistener2;
import com.steven.selectimage.widget.recyclerview.SpaceGridItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 反馈与建议
 */
public class activity_feedback extends BaseActivity {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    String sname;
    String sphone;
    String scontent;

    private ArrayList<Image> mSelectImages = new ArrayList<>();
    private SelectedImageAdapter mAdapter;
    private final String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private Image image;
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static final int SELECT_IMAGE_REQUEST = 0x0011;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_feedback.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_feedback;
    }

    @Override
    public void iniview() {

        //默认添加图片++
        image = new Image();
        image.setPath("");
        mSelectImages.add(image);

        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.addItemDecoration(new SpaceGridItemDecoration((int) TDevice.dipToPx(getResources(), 1)));
        recyclerview.setAdapter(mAdapter = new SelectedImageAdapter(context, mSelectImages)); //适配器加入布局
        mItemTouchHelper.attachToRecyclerView(recyclerview);//用于移动吸附着item
        mAdapter.setOnItemClicklistener2(new OnItemClicklistener2() {
            @Override
            public void onAdd() {
                selectImage(); //监听图片添加事件
            }

            @Override
            public void onItemClick(int position) {
                //图片预览
                PreviewImageActivity.setAction(context, mSelectImages, position);
            }

            @Override
            public void delonItem(int postion) {
                mSelectImages.remove(postion);
                mAdapter.notifyDataSetChanged();
                //插入最后一个图片
                if (!isgetboolean()) {
                    mSelectImages.add(image);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });//图片添加或点击事件监听
    }

    @Override
    public void initData() {

    }

    @Override

    @OnClick({R.id.send_post})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send_post:
                sname = name.getText().toString();
                sphone = phone.getText().toString();
                scontent = content.getText().toString();
                if (TextUtils.isEmpty(sname)) {
                    Toasts.toastMessage(getString(R.string.fee11));
                    return;
                }
                if (TextUtils.isEmpty(sphone)) {
                    Toasts.toastMessage(getString(R.string.fee12));
                    return;
                }
                if (TextUtils.isEmpty(scontent)) {
                    Toasts.toastMessage(getString(R.string.fee13));
                    return;
                }
                updadteImages();

                break;
        }

    }

    @Override
    public void OnEorr() {

    }

    private boolean isgetboolean() {
        for (Image mSelectImage : mSelectImages) {
            String path = mSelectImage.getPath();
            if (TextUtils.isEmpty(path)) {
                return true;
            }
        }
        return false;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity();
            } else {
                //申请权限
                ActivityCompat.requestPermissions(activity, PERMISSION, PERMISSION_REQUEST_CODE);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_REQUEST && data != null) {
                //获得选中的图片文件
                ArrayList<Image> selectImages = data.getParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT);
                mSelectImages.clear();

                //小于6张图片 加入添加相册+
                if (selectImages.size() < SelectImageActivity.MAX_SIZE) {
                    selectImages.add(image);
                }

                mSelectImages.addAll(selectImages);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 图片拖动排列
     */
    private final ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            // 获取触摸响应的方向   包含两个 1.拖动dragFlags 2.侧滑删除swipeFlags
            // 代表只能是向左侧滑删除，当前可以是这样ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
            int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int dragFlags;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        /**
         * 拖动的时候不断的回调方法
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //获取到原来的位置
            int fromPosition = viewHolder.getAdapterPosition();
            //获取到拖到的位置
            int targetPosition = target.getAdapterPosition();
            if (fromPosition < targetPosition) {
                for (int i = fromPosition; i < targetPosition; i++) {
                    Collections.swap(mSelectImages, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > targetPosition; i--) {
                    Collections.swap(mSelectImages, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, targetPosition);
            return true;
        }

        /**
         * 侧滑删除后会回调的方法
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            mSelectImages.remove(position);
            mAdapter.notifyItemRemoved(position);

            //插入最后一个图片
            if (!isgetboolean()) {
                mSelectImages.add(image);
                mAdapter.notifyDataSetChanged();
            }
        }
    });

    /**
     * 申请权限
     */
    private void selectImage() {
        int permission1 = ContextCompat.checkSelfPermission(activity, PERMISSION[0]);
        int permission2 = ContextCompat.checkSelfPermission(activity, PERMISSION[1]);
        if (permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED) {
            startActivity();
        } else {
            //申请读写权限
            ActivityCompat.requestPermissions(activity, PERMISSION, PERMISSION_REQUEST_CODE);
        }


    }

    /**
     * 转到相册选择页
     */
    private void startActivity() {
        SelectImageActivity.MAX_SIZE = 3;
        Intent intent = new Intent(context, SelectImageActivity.class);
        intent.putParcelableArrayListExtra("selected_images", mSelectImages);
        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    /**
     * 上传图片
     */
    private void updadteImages() {
        showDialog();
        for (Image image : mSelectImages) {
            //把默认空图排除
            if (TextUtils.isEmpty(image.getPath())) {
                mSelectImages.remove(image);
            }
        }
        if (mSelectImages.size() == 0) {
            //意见反馈不带图片
            sendclassTask("");
        } else {
            //意见反馈有带图片
            List<String> arrayList = new ArrayList<>();
            for (int i = 0; i < mSelectImages.size(); i++) {
                Image selectImage = mSelectImages.get(i);
                String path = selectImage.getPath();
                File file = new File(path);

                //Android 图片压缩之鲁班Luban详解
                Luban.with(context).load(file).ignoreBy(100).filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                }).setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        //执行上传图片 1 图片 2 wave 3 mp4 4 docx
                        Datamodule.getInstance().uploadImg(file, 1, new CallBcak() {
                            @Override
                            public void Onfall() {

                            }

                            @Override
                            public void OnSuccess(Object o) {
                                arrayList.add(o.toString().trim());
                                if (mSelectImages.size() == arrayList.size()) {
                                    String json = JsonUitl.objectToString(arrayList);
                                    //发布作业提交
                                    sendclassTask(json);

                                }


                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();

            }
        }
    }

    /**
     * 意见反馈
     *
     * @param s
     */
    private void sendclassTask(String s) {
        subadvice subadvice = new subadvice();
        subadvice.setId(userinfo.getId());
        subadvice.setTitles(sname);//标题
        subadvice.setDescribes(scontent);//描述
        subadvice.setContacts(sphone);//联系方式
        subadvice.setDetailsImg(s);//图片详情
        subadvice.setSources(1);//来源 1安卓 2苹果 0其它
        subadvice.setCreateTime(String.valueOf(System.currentTimeMillis()));
        Datamodule.getInstance().subadvice(gson.toJson(subadvice), new CallBcak() {
            @Override
            public void OnSuccess() {
                dismissDialog();
                //反馈与建议已提交
                activity_feedbabck.setAction(context);
                finish();
            }

            @Override
            public void Onfall() {

            }
        });
    }

}