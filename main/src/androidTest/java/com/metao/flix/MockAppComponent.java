package com.metao.flix;

import android.content.Context;

import com.metao.flix.di.ApplicationComponent;
import com.metao.flix.di.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import retrofit2.Retrofit;

/**
 * Created by matio on 03/02/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface MockAppComponent extends ApplicationComponent {
    Retrofit exposeRetrofit();

    Cache exposeCache();

    Context exposeContext();
}
