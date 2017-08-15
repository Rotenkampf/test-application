package com.test.antony.megakittest.ui.fragments.autoFragment;

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
import com.test.antony.megakittest.ui.base.BaseFragment;
import com.test.antony.megakittest.ui.view.creatorController.AutoCreatorController;
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

public class AutoFragment extends BaseFragment implements IAuto.View {

    @Inject
    IAuto.Presenter<IAuto.View> mPresenter;

    @BindView(R.id.layout_common_recycler)
    RecyclerView mAutoRecycler;
    @BindView(R.id.layout_common_add)
    FloatingActionButton mAddButton;
    @BindView(R.id.fragment_auto_create_auto)
    CardView mCreateAutoCard;
    @BindView(R.id.fragment_auto_create_owner)
    CardView mCreateOwnerCard;
    @BindView(R.id.fragment_auto_mask_layout)
    FrameLayout mMaskLayout;


    private AutoAdapter mAutoAdapter;
    private AutoCreatorController mAutoCreatorController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_auto, container, false);
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
        mAutoRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mAutoRecycler.getContext(),
                layoutManager.getOrientation());
        mAutoRecycler.addItemDecoration(dividerItemDecoration);
        mAutoAdapter=new AutoAdapter();
        mAutoRecycler.setAdapter(mAutoAdapter);
        mAddButton.setImageDrawable(new IconicsDrawable(getActivity()).icon(GoogleMaterial.Icon.gmd_add).color(Color.WHITE));
        mAutoCreatorController=new AutoCreatorController(mCreateAutoCard, mCreateOwnerCard);
        setupListeners();
        mPresenter.loadAutos();
    }


    @Override
    public void setAutos(List<AutoData> autos) {
        mAutoAdapter.setDataSet(autos);
    }

    @Override
    public void setOwners(List<OwnerData> owners) {
        mAutoCreatorController.setDataSet(owners);
    }

    @OnClick(R.id.layout_common_add)
    void addAuto(){
        if (mCreateAutoCard.getVisibility()==View.GONE){
            mCreateAutoCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down));
            mCreateAutoCard.setVisibility(View.VISIBLE);
            mMaskLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupListeners(){
        mMaskLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mCreateOwnerCard.getVisibility()==View.VISIBLE){
                    mAutoCreatorController.cancelNested();
                    mCreateOwnerCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down));
                    mCreateOwnerCard.setVisibility(View.GONE);
                    mCreateAutoCard.setVisibility(View.VISIBLE);
                }
                if (mCreateAutoCard.getVisibility()==View.VISIBLE){
                    hideMainView();
                }
                return true;
            }
        });
        mAutoCreatorController.setCreatorListener(new CreatorListener() {
            @Override
            public void onCancel() {
                hideMainView();
            }

            @Override
            public void onError(String message) {
                handleError(message);
            }
        });
        mAutoCreatorController
                .registerSaveObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AutoData>() {
                    @Override
                    public void accept(@NonNull AutoData autoData) throws Exception {
                        mPresenter.saveAuto(autoData);
                        mAutoCreatorController.cancel();
                        hideMainView();
                        mPresenter.loadAutos();
                    }
                });
        mAutoAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                mAutoCreatorController.setData(mAutoAdapter.getDataSet().get(position));
                showMainView();
            }

            @Override
            public void onItemDeleted(int position) {
                mPresenter.deleteAuto(mAutoAdapter.getDataSet().get(position).getId());
            }
        });
    }

    private void showMainView(){
        mCreateAutoCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_down));
        mCreateAutoCard.setVisibility(View.VISIBLE);
        mMaskLayout.setVisibility(View.VISIBLE);
        mPresenter.loadOwners();
    }

    private void hideMainView(){
        mMaskLayout.setVisibility(View.GONE);
        mCreateAutoCard.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down));
        mCreateAutoCard.setVisibility(View.GONE);
        mAutoCreatorController.cancel();
    }

}
