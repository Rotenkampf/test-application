package com.test.antony.megakittest.ui.fragments.autoFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.ui.adapters.AutoAdapter;
import com.test.antony.megakittest.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 11.08.17.
 */

public class AutoFragment extends BaseFragment implements IAuto.View {

    @Inject
    IAuto.Presenter<IAuto.View> mPresenter;

    @BindView(R.id.fragment_auto_recycler)
    RecyclerView mAutoRecycler;


    private AutoAdapter mAutoAdapter;

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
    }
}
