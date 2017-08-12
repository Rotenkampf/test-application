package com.test.antony.megakittest.ui.base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.antony.megakittest.App;
import com.test.antony.megakittest.di.component.DaggerFragmentComponent;
import com.test.antony.megakittest.di.component.FragmentComponent;
import com.test.antony.megakittest.di.module.FragmentModule;

import butterknife.Unbinder;

/**
 * Created by admin on 11.08.17.
 */

public class BaseFragment extends Fragment implements IBase.View{

    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("MissingSuperCall") // super method exist only in sdk>21
    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            super.onAttach(context);
        }
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached(this);
        }
        mFragmentComponent= DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(App.get(this.mActivity).getComponent())
                .build();
    }

    @Override
    public void onDetach() {
        if (mActivity!=null) {
            mActivity.onFragmentDetached();
        }
        mActivity = null;
        super.onDetach();
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
//        try {
//            App.getRefWatcher(getBaseActivity()).watch(this);
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
        super.onDestroy();
    }

    public String getTagString(){
        return BaseFragment.class.getSimpleName();
    }


    @Override
    public void handleError(String message) {

    }

    @Override
    public Context getLocalContext() {
        return getBaseActivity();
    }
}
