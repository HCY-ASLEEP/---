package com.example.final_work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    public ViewPagerAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        return RecycleViewFragment.newInstance();
        switch (position){
            case 0:
                return RecycleViewFragment.newInstance(R.array.food,"厨余垃圾",R.drawable.food);
            case 1:
                return RecycleViewFragment.newInstance(R.array.harmful,"有害垃圾",R.drawable.harmful);
            case 2:
                return RecycleViewFragment.newInstance(R.array.recycle,"可回收垃圾",R.drawable.recycle);
            default:
                return RecycleViewFragment.newInstance(R.array.other,"其他垃圾",R.drawable.other);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "厨余垃圾";
            case 1:
                return "有害垃圾";
            case 2:
                return "可回收垃圾";
            default:
                return "其他垃圾";
        }
    }
}
