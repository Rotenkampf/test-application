package com.test.antony.megakittest.di.component;

import com.test.antony.megakittest.di.PerFragment;
import com.test.antony.megakittest.di.module.FragmentModule;
import com.test.antony.megakittest.ui.fragments.autoFragment.AutoFragment;

import dagger.Component;

/**
 * Created by admin on 11.08.17.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(AutoFragment autoFragment);

}
