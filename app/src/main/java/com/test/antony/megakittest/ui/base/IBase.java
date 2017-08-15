package com.test.antony.megakittest.ui.base;

import android.content.Context;

/**
 * Created by Antony Mosin
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
