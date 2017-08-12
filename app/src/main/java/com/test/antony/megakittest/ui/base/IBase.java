package com.test.antony.megakittest.ui.base;

import android.content.Context;

/**
 * Created by admin on 11.08.17.
 */

public interface IBase {

    interface View{
        void handleError(String message);

        Context getLocalContext();
    }

    interface Presenter<V extends IBase.View>{

        void onAttach(V view);

        void onDetach();

    }

}
