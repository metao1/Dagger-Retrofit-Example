package com.metao.flix.di;

import android.app.Application;

import com.metao.flix.mvp.DepartureView;
import com.metao.flix.service.FlixService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Mehrdad A.Karami  28/25.
 */

@Module
public class DepartureModule {

    private DepartureView mDriversView;

    private Application mApplication;

    public DepartureModule(Application application, DepartureView departuresView) {
        mDriversView = departuresView;
        mApplication = application;
    }

    public DepartureModule( DepartureView departuresView) {
        mDriversView = departuresView;
    }

    @PerActivity
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @PerActivity
    @Provides
    FlixService provideDriversService(Retrofit retrofit) {
        return retrofit.create(FlixService.class);
    }

    @PerActivity
    @Provides
    DepartureView provideView() {
        return mDriversView;
    }

}
