package com.test.antony.megakittest.data;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Antony Mosin
 */

public interface IDataManager {

    void addAuto(AutoData autoData);

    Observable<List<AutoData>> getAutos();

    Observable<List<OwnerData>> getOwners();

}
