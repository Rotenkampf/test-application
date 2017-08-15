package com.test.antony.megakittest.ui.base;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by Antony Mosin
 */

public class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    public void onFragmentAttached(Fragment fragment){

    }

    public void onFragmentDetached(){

    }

}
