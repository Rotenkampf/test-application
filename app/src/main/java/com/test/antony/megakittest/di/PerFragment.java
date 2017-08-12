package com.test.antony.megakittest.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by admin on 10.08.17.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
