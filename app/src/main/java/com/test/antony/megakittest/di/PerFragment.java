package com.test.antony.megakittest.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Antony Mosin
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
