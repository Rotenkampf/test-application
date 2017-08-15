/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.fragments.ownerFragment;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.base.IBase;

import java.util.List;

/**
 * Created by Antony Mosin
 */

public interface IOwner {

    interface View extends IBase.View{
        void setAutos(List<AutoData> autos);
        void setOwners(List<OwnerData> owners);
    }

    interface Presenter<V extends IOwner.View> extends IBase.Presenter<V>{

        void saveOwner(OwnerData ownerData);
        void loadAutos();
        void loadOwners();
        void deleteOwner(String ownerId);

    }


}
