package com.android.learn.learnandroid.activity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.learn.learnandroid.R;
import com.android.learn.learnandroid.network.Weather;
import com.android.learn.learnandroid.network.WeatherService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

//Reference: https://medium.com/@ahmedrizwan/rxandroid-and-retrofit-2-0-66dc52725fff#.dik7vjurc
//Reference: http://dev.classmethod.jp/smartphone/android/okhttp-retrofit-rxandroid/
//Test page: http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a

public class RetrofitRxActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_oknetwork);
    testJsonGet();
  }

  private void testJsonGet() {
    OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(
        new StethoInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(client).baseUrl("http://api.openweathermap.org/data/2.5/")
        .build();

    WeatherService weatherService = retrofit.create(WeatherService.class);
    Observable<Weather> london = weatherService.getCurrent("London,uk",
        "b1b15e88fa797225412429c1c50c122a");

    london.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Weather>() {
          @Override
          public void onCompleted() {
          }

          @Override
          public void onError(Throwable e) {
          }

          @Override
          public void onNext(Weather weather) {
            // london stations
            _Debug("info: " + weather.getName() + " " + weather.getBase());
            // haze
            _Debug("description: "
                + weather.getWeather().get(0).getDescription());
            // 1034 277.52
            _Debug("pressure: " + weather.getMain().getPressure() + " temp: "
                + weather.getMain().getTemp());
          }
        });
  }

  private static void _Debug(String string) {
    Log.d("widget", string);
  }
}

// {"coord":{"lon":-0.13,"lat":51.51},"weather":[{"id":721,"main":"Haze","description":"haze","icon":"50d"}],"base":"stations","main":{"temp":277.52,"pressure":1034,"humidity":70,"temp_min":276.15,"temp_max":279.15},"visibility":10000,"wind":{"speed":6.7,"deg":80},"clouds":{"all":75},"dt":1457942721,"sys":{"type":1,"id":5091,"message":0.0161,"country":"GB","sunrise":1457936117,"sunset":1457978677},"id":2643743,"name":"London","cod":200}
