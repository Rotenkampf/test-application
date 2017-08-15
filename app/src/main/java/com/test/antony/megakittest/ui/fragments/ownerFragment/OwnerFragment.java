/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.fragments.ownerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.ui.base.BaseFragment;

import javax.inject.Inject;


/**
 * Created by Antony Mosin
 */

public class OwnerFragment extends BaseFragment {

    @Inject
    IOwner.Presenter<IOwner.View> mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_auto, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




}
