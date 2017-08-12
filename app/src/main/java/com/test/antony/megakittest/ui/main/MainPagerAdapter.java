package com.test.antony.megakittest.ui.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by admin on 11.08.17.
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
            case 0: return new Fragment();
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
