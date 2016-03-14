package com.android.learn.learnandroid.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherService {

  @GET("weather?")
  Observable<Weather> getCurrent(@Query("q") String city, @Query("appid") String id);

}
