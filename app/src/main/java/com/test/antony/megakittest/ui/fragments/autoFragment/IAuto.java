package com.test.antony.megakittest.ui.fragments.autoFragment;

import com.test.antony.megakittest.ui.base.IBase;

/**
 * Created by admin on 11.08.17.
 */

public interface IAuto {

    interface View extends IBase.View{

    }

    interface Presenter<V extends IAuto.View> extends IBase.Presenter<V>{

    }

}
