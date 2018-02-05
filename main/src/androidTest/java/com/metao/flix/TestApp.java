package com.metao.flix;

import com.metao.flix.app.Flixpplication;
import com.metao.flix.di.ApplicationComponent;
import com.metao.flix.di.ApplicationModule;
import com.metao.flix.di.DaggerApplicationComponent;

/**
 * Created by matio on 02/02/18.
 */

public class TestApp extends Flixpplication {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getBaseContext(), BuildConfig.BASE_URL))
                .build();
    }

    @Override
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
