package com.test.antony.megakittest.ui.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.test.antony.megakittest.ui.fragments.autoFragment.AutoFragment;

/**
 * Created by Antony Mosin
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT=2;
    private String[] tabTitles=new String[]{"Авто", "Владельцы"};

    public MainPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new AutoFragment();
            case 1: return new Fragment();
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
