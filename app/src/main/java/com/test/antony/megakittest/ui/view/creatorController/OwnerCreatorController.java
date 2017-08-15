package com.test.antony.megakittest.ui.view.creatorController;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.view.AutoCompletionView;
import com.test.antony.megakittest.utils.listeners.CreatorListener;
import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.realm.RealmList;

/**
 * Created by Antony Mosin
 */

public class OwnerCreatorController extends AbstractCreatorController<OwnerData, AutoData>{

    private MaterialEditText mNameEditText;
    private MaterialEditText mExperienceEditText;
    private AutoCompletionView mAutoCompletionView;
    private Button mSaveButton;
    private Button mCancelButton;
    private Button mAddNewAutoButton;

    private AutoCreatorController mAutoCreatorController;
    private List<AutoData> mSelectedAutos=new ArrayList<>();
    private Disposable mNestedSaveDisposable;

    public OwnerCreatorController(View ownerView, @Nullable View autoView) {
        super(ownerView, autoView);
    }

    @Override
    protected void bindViews(){
        mNameEditText=(MaterialEditText) getMainView().findViewById(R.id.card_owner_create_name);
        mExperienceEditText =(MaterialEditText) getMainView().findViewById(R.id.card_owner_create_experience);
        mAutoCompletionView =(AutoCompletionView) getMainView().findViewById(R.id.card_owner_create_auto);
        mSaveButton=(Button) getMainView().findViewById(R.id.card_owner_create_save);
        mCancelButton=(Button) getMainView().findViewById(R.id.card_owner_create_cancel);
        mAddNewAutoButton =(Button) getMainView().findViewById(R.id.card_owner_create_new_auto);
        if (getNestedView()!=null) {
            mAutoCreatorController = new AutoCreatorController(getNestedView(), null);
            mAutoCreatorController.setNested(true);
        }
        setupListeners();
    }

    @Override
    protected void setupCustomListeners(){
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCreatorListener()!=null){
                    getCreatorListener().onCancel();
                }
            }
        });
        mAddNewAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNestedView().startAnimation(AnimationUtils.loadAnimation(getMainView().getContext(), R.anim.slide_in_down));
                getNestedView().setVisibility(View.VISIBLE);
                getMainView().setVisibility(View.GONE);
            }
        });
        mAutoCompletionView.setTokenListener(new TokenCompleteTextView.TokenListener<AutoData>() {
            @Override
            public void onTokenAdded(AutoData token) {
                mSelectedAutos.add(token);
            }

            @Override
            public void onTokenRemoved(AutoData token) {
                mSelectedAutos.remove(token);
            }
        });
        mAutoCompletionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAutoCompletionView.showDropDown();
            }
        });
    }

    @Override
    protected void setupNestedListeners() {
        mNestedSaveDisposable = mAutoCreatorController
                .registerSaveObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AutoData>() {
                    @Override
                    public void accept(@NonNull AutoData autoData) throws Exception {
                        mSelectedAutos.add(autoData);
                        mAutoCompletionView.addObject(autoData);
                        hideNestedView();
                    }
                });
        mAutoCreatorController.setCreatorListener(new CreatorListener() {
            @Override
            public void onCancel() {
                hideNestedView();
                mAutoCreatorController.cancel();
            }

            @Override
            public void onError(String message) {
                getCreatorListener().onError(message);
            }
        });
    }

    @Override
    public void setData(OwnerData data) {
        super.setData(data);
        mNameEditText.setText(data.getName());
        mExperienceEditText.setText(Integer.toString(data.getExperience()));
        for (AutoData autoData:data.getAutos()) {
            mAutoCompletionView.addObject(autoData);
        }
    }

    @Override
    public void setDataSet(List<AutoData> dataSet) {
        mAutoCompletionView.setAdapter(new ArrayAdapter<>(getMainView().getContext(), android.R.layout.simple_dropdown_item_1line, dataSet));
    }

    @Override
    public Observable<OwnerData> registerSaveObservable(){
        return Observable.create(new ObservableOnSubscribe<OwnerData>() {
            @Override
            public void subscribe(final ObservableEmitter<OwnerData> e) throws Exception {
                mSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!e.isDisposed()){
                            if (isValid()) {
                                e.onNext(formSaveData());
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    protected OwnerData formSaveData(){
        OwnerData ownerData;
        if (getSavedData()!=null){
            ownerData=getSavedData();
        } else {
            ownerData=new OwnerData();
            ownerData.setId(UUID.randomUUID().toString());
        }
        ownerData.setName(mNameEditText.getText().toString());
        ownerData.setExperience(Integer.parseInt(mExperienceEditText.getText().toString()));
        ownerData.setAutos(new RealmList<AutoData>());
        for (AutoData autoData:mSelectedAutos){
            autoData.setOwner(ownerData);
        }
        ownerData.getAutos().addAll(mSelectedAutos);
        return ownerData;
    }

    @Override
    protected boolean isValid(){
        if (mNameEditText.getText().toString().isEmpty()){
            getCreatorListener().onError("Не указано имя");
            return false;
        }
        if (mExperienceEditText.getText().toString().isEmpty()){
            getCreatorListener().onError("Не указан стаж");
            return false;
        }
        if (!isNested() && mSelectedAutos.size()==0){
            getCreatorListener().onError("Не указаны авто");
            return false;
        }
        return true;
    }

    @Override
    public void setNested(boolean nested) {
        super.setNested(nested);
        mAddNewAutoButton.setVisibility(View.GONE);
        mAutoCompletionView.setVisibility(View.GONE);
    }

    @Override
    public void cancel(){
        mNameEditText.setText("");
        mExperienceEditText.setText("");
        mAutoCompletionView.clear();
        mSelectedAutos.clear();
        if (mAutoCreatorController!=null){
            cancelNested();
        }
    }

    @Override
    public void cancelNested(){
        mAutoCreatorController.cancel();
    }

}
