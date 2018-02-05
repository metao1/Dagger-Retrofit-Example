package com.metao.flix.di;

import com.metao.flix.app.MainActivity;

import dagger.Component;

/**
 * Created by Mehrdad A.Karami  28/25.
 */
@PerActivity
@Component(modules = DepartureModule.class, dependencies = ApplicationComponent.class)
public interface DepartureComponent {

    void inject(MainActivity mainActivity);

}
