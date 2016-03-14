package com.android.learn.learnandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.learn.learnandroid.R;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//Reference: https://github.com/codepath/android_guides/wiki/Using-OkHttp
//Test page:https://httpbin.org/get?show_env=2.0

public class OkNetworkActivity extends Activity {

  private static boolean NETWORK_DEBUG = true;
  private TextView tv;
  private OkHttpClient mHttpClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_oknetwork);

    tv = (TextView) findViewById(R.id.hellostring);
    ImageView iv = (ImageView) findViewById(R.id.imageView1);

    if (NETWORK_DEBUG) {
      mHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(
          new StethoInterceptor()).build();
    } else {
      mHttpClient = new OkHttpClient();
    }

    Picasso picasso = new Picasso.Builder(OkNetworkActivity.this).downloader(
        new OkHttp3Downloader(mHttpClient)).build();
    picasso.load("http://square.github.io/picasso/static/sample.png")
        .placeholder(R.drawable.ic_updown).into(iv);

    testHttpGet();
  }

  private void testHttpGet() {
    HttpUrl.Builder urlBuilder = HttpUrl.parse("https://httpbin.org/get")
        .newBuilder();
    urlBuilder.addQueryParameter("show_env", "1.0");
    String url = urlBuilder.build().toString();
    Request request = new Request.Builder().url(url).build();

    if (mHttpClient != null) {
      mHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          final String responseData = response.body().string();
          OkNetworkActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              tv.setText(responseData);
            }
          });
        }
      });
    }
  }
}
