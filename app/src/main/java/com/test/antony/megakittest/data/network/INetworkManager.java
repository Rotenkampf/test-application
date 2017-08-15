package com.test.antony.megakittest.data.network;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;

/**
 * Created by Antony Mosin
 */

public interface INetworkManager {

    void sendAuto(AutoData autoData);

    void sendOwner(OwnerData ownerData);

}
