package com.test.antony.megakittest.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.tokenautocomplete.TokenCompleteTextView;

/**
 * Created by Antony Mosin
 */

public class AutoCompletionView extends TokenCompleteTextView<AutoData> {

    public AutoCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View getViewForObject(AutoData autoData) {

        LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        TextView view = (TextView) l.inflate(R.layout.token_auto, (ViewGroup) getParent(), false);
        view.setText(autoData.getName());

        return view;
    }

    @Override
    protected AutoData defaultObject(String completionText) {
        return new AutoData();
    }
}