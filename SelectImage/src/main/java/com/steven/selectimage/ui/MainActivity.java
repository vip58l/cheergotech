package com.steven.selectimage.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.steven.selectimage.R;
import com.steven.selectimage.model.Image;
import com.steven.selectimage.ui.adapter.SelectedImageAdapter;
import com.steven.selectimage.utils.TDevice;
import com.steven.selectimage.widget.recyclerview.OnAddPicturesListener;
import com.steven.selectimage.widget.recyclerview.SpaceGridItemDecoration;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends BaseActivity {
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static final int SELECT_IMAGE_REQUEST = 0x0011;
    private final String TAG = "MainActivity";

    private RecyclerView recyclerview;
    private TextView mDragTip;
    private ArrayList<Image> mSelectImages;
    private SelectedImageAdapter mAdapter;
    private final String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private Image image;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        recyclerview = findViewById(R.id.recyclerview);
        mDragTip = findViewById(R.id.drag_tip);

        mSelectImages = new ArrayList<>();
        image = new Image();
        image.setPath("");
        mSelectImages.add(image);

        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerview.addItemDecoration(new SpaceGridItemDecoration((int) TDevice.dipToPx(getResources(), 1)));
        recyclerview.setAdapter(mAdapter = new SelectedImageAdapter(this, mSelectImages, R.layout.selected_image_item)); //?????????????????????
        mItemTouchHelper.attachToRecyclerView(recyclerview);                                                                      //?????????????????????item
        mAdapter.setListener(new OnAddPicturesListener() {
            @Override
            public void onAdd() {
                selectImage();
            }
        });

    }

    /**
     * ????????????
     */
    private void selectImage() {
        int permission1 = ContextCompat.checkSelfPermission(this, PERMISSION[0]);
        int permission2 = ContextCompat.checkSelfPermission(this, PERMISSION[1]);
        if (permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED) {
            startActivity();
        } else {
            //??????????????????
            ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_REQUEST_CODE);
        }


    }

    /**
     * ?????????????????????
     */
    private void startActivity() {
        Intent intent = new Intent(this, SelectImageActivity.class);
        intent.putParcelableArrayListExtra("selected_images", mSelectImages);
        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity();
            } else {
                //????????????
                ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_REQUEST_CODE);
                Toast.makeText(this, "????????????????????????!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_REQUEST && data != null) {
                ArrayList<Image> selectImages = data.getParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT);
                mSelectImages.clear();
                if (selectImages.size() < 9) {
                    selectImages.add(image);
                }
                mSelectImages.addAll(selectImages);
                mDragTip.setVisibility(mSelectImages.size() > 1 ? View.VISIBLE : View.GONE);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * ??????????????????
     */
    private final ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            // ???????????????????????????   ???????????? 1.??????dragFlags 2.????????????swipeFlags
            // ?????????????????????????????????????????????????????????ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
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
         * ????????????????????????????????????
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //????????????????????????
            int fromPosition = viewHolder.getAdapterPosition();
            //????????????????????????
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
         * ?????????????????????????????????
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            mSelectImages.remove(position);
            mAdapter.notifyItemRemoved(position);

            //????????????????????????
            if (!isgetboolean()) {
                mSelectImages.add(image);
                mAdapter.notifyDataSetChanged();
            }
        }
    });

    private boolean isgetboolean() {
        for (Image mSelectImage : mSelectImages) {
            String path = mSelectImage.getPath();
            if (TextUtils.isEmpty(path)) {
                return true;
            }
        }
        return false;
    }

}
