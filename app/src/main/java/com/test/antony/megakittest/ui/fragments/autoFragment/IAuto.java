package com.test.antony.megakittest.ui.fragments.autoFragment;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.base.IBase;

import java.util.List;

/**
 * Created by Antony Mosin
 */

public interface IAuto {

    interface View extends IBase.View{
        void setAutos(List<AutoData> autos);
        void setOwners(List<OwnerData> owners);
    }

    interface Presenter<V extends IAuto.View> extends IBase.Presenter<V>{

        void saveAuto(AutoData autoData);
        void loadAutos();
        void loadOwners();
        void deleteAuto(String autoId);

    }

}
