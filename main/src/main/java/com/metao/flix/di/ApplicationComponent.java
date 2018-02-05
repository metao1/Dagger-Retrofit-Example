package com.metao.flix.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import retrofit2.Retrofit;

/**
 * Created by Mehrdad A.Karami  28/25.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Cache exposeCache();

    Context exposeContext();
}
