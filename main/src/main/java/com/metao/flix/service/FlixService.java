package com.metao.flix.service;

import com.metao.flix.models.Response;

import javax.inject.Singleton;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mehrdad A.Karami  28/25.
 */
@Singleton
public interface FlixService {

    @GET("/mobile/v1/network/station/10/timetable")
    public Observable<Response> getDepartures();
}
