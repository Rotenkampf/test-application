package com.test.antony.megakittest.ui.view.creatorController;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.utils.listeners.CreatorListener;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Antony Mosin
 */

public class AutoCreatorController extends AbstractCreatorController<AutoData, OwnerData> {

    private MaterialEditText mNameEditText;
    private MaterialEditText mNumberEditText;
    private MaterialAutoCompleteTextView mOwnerAutoComplete;
    private Button mSaveButton;
    private Button mCancelButton;
    private Button mAddNewOwnerButton;

    private OwnerCreatorController mOwnerCreatorController;
    private boolean mIsNested;
    private Disposable mNestedSaveDisposable;
    private OwnerData mSelectedOwner;

    public AutoCreatorController(View autoView, @Nullable View ownerView) {
        super(autoView, ownerView);
    }

    @Override
    protected void bindViews(){
        mNameEditText=(MaterialEditText) getMainView().findViewById(R.id.card_auto_create_name);
        mNumberEditText=(MaterialEditText) getMainView().findViewById(R.id.card_auto_create_number);
        mOwnerAutoComplete=(MaterialAutoCompleteTextView) getMainView().findViewById(R.id.card_auto_create_owner);
        mSaveButton=(Button) getMainView().findViewById(R.id.card_auto_create_save);
        mCancelButton=(Button) getMainView().findViewById(R.id.card_auto_create_cancel);
        mAddNewOwnerButton=(Button) getMainView().findViewById(R.id.card_auto_create_new_owner);
        if (getNestedView()!=null) {
            mOwnerCreatorController = new OwnerCreatorController(getNestedView(), null);
            mOwnerCreatorController.setNested(true);
        }
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
        mAddNewOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNestedView().startAnimation(AnimationUtils.loadAnimation(getMainView().getContext(), R.anim.slide_in_down));
                getNestedView().setVisibility(View.VISIBLE);
                getMainView().setVisibility(View.GONE);
            }
        });
        mOwnerAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOwnerAutoComplete.showDropDown();
            }
        });
    }

    @Override
    protected void setupNestedListeners() {
        mNestedSaveDisposable = mOwnerCreatorController
                .registerSaveObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OwnerData>() {
                    @Override
                    public void accept(@NonNull OwnerData ownerData) throws Exception {
                        mSelectedOwner = ownerData;
                        mOwnerAutoComplete.setText(ownerData.getName());
                        hideNestedView();
                    }
                });
        mOwnerCreatorController.setCreatorListener(new CreatorListener() {
            @Override
            public void onCancel() {
                hideNestedView();
            }

            @Override
            public void onError(String message) {
                getCreatorListener().onError(message);
            }
        });
    }

    @Override
    public Observable<AutoData> registerSaveObservable(){
        return Observable.create(new ObservableOnSubscribe<AutoData>() {
            @Override
            public void subscribe(final ObservableEmitter<AutoData> e) throws Exception {
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
    public void setData(AutoData autoData){
        super.setData(autoData);
        mNameEditText.setText(autoData.getName());
        mNumberEditText.setText(autoData.getNumber());
        mSelectedOwner=autoData.getOwner();
        mOwnerAutoComplete.setText(autoData.getOwner().getName());
    }


    @Override
    public void setDataSet(List<OwnerData> dataSet) {
        mOwnerAutoComplete.setAdapter(new ArrayAdapter<>(getMainView().getContext(), android.R.layout.simple_dropdown_item_1line, dataSet));
    }

    @Override
    protected AutoData formSaveData(){
        AutoData autoData;
        if (getSavedData()==null) {
            autoData = new AutoData();
            autoData.setId(UUID.randomUUID().toString());
        } else {
            autoData=getSavedData();
        }
        autoData.setName(mNameEditText.getText().toString());
        autoData.setNumber(mNumberEditText.getText().toString());
        if (!mSelectedOwner.getAutos().contains(autoData)) {
            mSelectedOwner.getAutos().add(autoData);
        }
        autoData.setOwner(mSelectedOwner);
        return autoData;
    }

    @Override
    public void cancel(){
        mNameEditText.setText("");
        mNumberEditText.setText("");
        mOwnerAutoComplete.setText("");
        mSelectedOwner=null;
        if (mOwnerCreatorController!=null){
            cancelNested();
        }
    }

    @Override
    public void cancelNested(){
        mOwnerCreatorController.cancel();
    }

    @Override
    protected boolean isValid(){
        if (mNameEditText.getText().toString().isEmpty()){
            getCreatorListener().onError("Не указано имя");
            return false;
        }
        if (!mNumberEditText.getText().toString().isEmpty()){
            getCreatorListener().onError("Не указаны номера");
        }
        if (!mIsNested && mSelectedOwner==null){
            getCreatorListener().onError("Не указан владелец");
            return false;
        }
        return true;
    }

    public void setNested(boolean nested) {
        mIsNested = nested;
        if (nested) {
            mAddNewOwnerButton.setVisibility(View.GONE);
            mOwnerAutoComplete.setVisibility(View.GONE);
        } else {
            mAddNewOwnerButton.setVisibility(View.VISIBLE);
            mOwnerAutoComplete.setVisibility(View.VISIBLE);
        }
    }
}
