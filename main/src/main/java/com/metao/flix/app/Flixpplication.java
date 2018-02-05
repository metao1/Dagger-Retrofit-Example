package com.metao.flix.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.metao.flix.BuildConfig;
import com.metao.flix.di.ApplicationComponent;
import com.metao.flix.di.ApplicationModule;
import com.metao.flix.di.DaggerApplicationComponent;

/**
 * Created by Mehrdad A.Karami  28/25.
 */
public class Flixpplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getBaseContext(), BuildConfig.BASE_URL))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
