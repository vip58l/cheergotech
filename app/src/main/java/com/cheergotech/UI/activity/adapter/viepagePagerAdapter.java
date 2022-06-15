package com.cheergotech.UI.activity.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.R;

import java.util.List;

public class viepagePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private int type;
    public static final int activity_student = 1001;
    private Context context;

    public viepagePagerAdapter(@NonNull FragmentManager fm, List<Fragment> list, int type) {
        super(fm);
        this.list = list;
        this.type = type;
        this.context = DemoApplication.instance();

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = list.get(position);
        return fragment;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (type) {
            case activity_student:
                String[] stringArray = context.getResources().getStringArray(R.array.table);
                return stringArray[position];
        }

        return "";
    }
}
