/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.fragments.ownerFragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.github.clans.fab.FloatingActionButton;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.adapters.AutoAdapter;
import com.test.antony.megakittest.ui.adapters.OwnerAdapter;
import com.test.antony.megakittest.ui.base.BaseFragment;
import com.test.antony.megakittest.ui.view.creatorController.AutoCreatorController;
import com.test.antony.megakittest.ui.view.creatorController.OwnerCreatorController;
import com.test.antony.megakittest.utils.listeners.CreatorListener;
import com.test.antony.megakittest.utils.listeners.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by Antony Mosin
 */

public class OwnerFragment extends BaseFragment implements IOwner.View{

    @Inject
    IOwner.Presenter<IOwner.View> mPresenter;


    @BindView(R.id.layout_common_recycler)
    RecyclerView mOwnerRecycler;
    @BindView(R.id.layout_common_add)
    FloatingActionButton mAddButton;
    @BindView(R.id.fragment_owner_create_auto)
    CardView mCreateAutoCard;
    @BindView(R.id.fragment_owner_create_owner)
    CardView mCreateOwnerCard;
    @BindView(R.id.fragment_owner_mask_layout)
    FrameLayout mMaskLayout;


    private OwnerAdapter mOwnerAdapter;
    private OwnerCreatorController mOwnerCreatorController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_owner, container, false);
        onAttach((Context) getActivity());
        getFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mOwnerRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mOwnerRecycler.getContext(),
                layoutManager.getOrientation());
        mOwnerRecycler.addItemDecoration(dividerItemDecoration);
        mOwnerAdapter=new OwnerAdapter();
        mOwnerRecycler.setAdapter(mOwnerAdapter);
        mAddButton.setImageDrawable(new IconicsDrawable(getActivity()).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE));
        mOwnerCreatorController=new OwnerCreatorController(mCreateOwnerCard, mCreateAutoCard);
        setupListeners();
        mPresenter.loadOwners();
    }


    @Override
    public void setAutos(List<AutoData> autos) {
        mOwnerCreatorController.setDataSet(autos);
    }

    @Override
    public void setOwners(List<OwnerData> owners) {
        mOwnerAdapter.setDataSet(owners);
    }

    @OnClick(R.id.layout_common_add)
    void addOwner(){
        if (mCreateAutoCard.getVisibility()==View.GONE){
            mCreateOwnerCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down));
            mCreateOwnerCard.setVisibility(View.VISIBLE);
            mMaskLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupListeners(){
        mMaskLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mCreateAutoCard.getVisibility()==View.VISIBLE){
                    mOwnerCreatorController.cancelNested();
                    mCreateAutoCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down));
                    mCreateAutoCard.setVisibility(View.GONE);
                    mCreateOwnerCard.setVisibility(View.VISIBLE);
                }
                if (mCreateOwnerCard.getVisibility()==View.VISIBLE){
                    hideMainView();
                }
                return true;
            }
        });
        mOwnerCreatorController.setCreatorListener(new CreatorListener() {
            @Override
            public void onCancel() {
                hideMainView();
            }

            @Override
            public void onError(String message) {
                handleError(message);
            }
        });
        mOwnerCreatorController
                .registerSaveObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OwnerData>() {
                    @Override
                    public void accept(@NonNull OwnerData ownerData) throws Exception {
                        mPresenter.saveOwner(ownerData);
                        mOwnerCreatorController.cancel();
                        hideMainView();
                        mPresenter.loadOwners();
                    }
                });
        mOwnerAdapter.setItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClicked(int position) {
                mOwnerCreatorController.setData(mOwnerAdapter.getDataSet().get(position));
                showMainView();
            }

            @Override
            public void onItemDeleted(int position) {
                mPresenter.deleteOwner(mOwnerAdapter.getDataSet().get(position).getId());
            }
        });
    }

    private void showMainView(){
        mCreateOwnerCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down));
        mCreateOwnerCard.setVisibility(View.VISIBLE);
        mMaskLayout.setVisibility(View.VISIBLE);
        mPresenter.loadAutos();
    }

    private void hideMainView(){
        mMaskLayout.setVisibility(View.GONE);
        mCreateOwnerCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down));
        mCreateOwnerCard.setVisibility(View.GONE);
        mOwnerCreatorController.cancel();
    }


}
