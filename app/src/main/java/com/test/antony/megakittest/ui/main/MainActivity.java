package com.test.antony.megakittest.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_pager)
    ViewPager mPager;
    @BindView(R.id.main_tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        mPager.setAdapter(new MainPagerAdapter(getFragmentManager(), this));
        mTabLayout.setupWithViewPager(mPager);
    }
}
