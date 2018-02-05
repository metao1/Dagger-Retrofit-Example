package com.metao.flix.mvp;

import com.metao.flix.models.Response;
import com.metao.flix.service.FlixService;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.functions.Func0;

/**
 * Created by Mehrdad A.Karami  28/25.
 */
public class DeparturePresenter extends MainPresenter<DepartureView> implements Observer<Response> {

    @Inject
    public FlixService mApiService;

    @Inject
    DeparturePresenter() {

    }

    public void getDepartures() {
        getView().onLoading();
        subscribe(getDriversObserver(), this);
    }

    private Observable<Response> getDriversObserver() {
        return Observable.defer(new Func0<Observable<Response>>() {
            @Override
            public Observable<Response> call() {
                return mApiService.getDepartures();
            }
        });
    }

    @Override
    public void onCompleted() {
        getView().onDeparturesLoad();
    }

    @Override
    public void onError(Throwable e) {
        getView().onErrorLoadingDepartures(e);
    }

    @Override
    public void onNext(Response response) {
        getView().onNextDeparturesLoad(response);
    }

}
