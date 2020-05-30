package com.example.introapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 5; //number of tabs representing number of fragments hosted by CMSPortalActivity

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //for each tab chosen, the ViewPager2 Adapter creates a new fragment, hosted within CMSPortalActivity
    @NonNull @Override public Fragment createFragment(int position) {
        return CMSTabFragment.newInstance(position);
    }

    //getting the number of fragments
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}