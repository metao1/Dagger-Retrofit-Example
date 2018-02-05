package com.metao.flix.mvp;

import com.metao.flix.models.Response;

/**
 * Created by Mehrdad A.Karami  28/25.
 */
public interface DepartureView {

    public void onDeparturesLoad();

    public void onLoading();

    public void onNextDeparturesLoad(Response response);

    public void onErrorLoadingDepartures(Throwable e);
}
