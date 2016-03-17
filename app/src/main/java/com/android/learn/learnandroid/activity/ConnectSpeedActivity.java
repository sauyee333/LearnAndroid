package com.android.learn.learnandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.learn.learnandroid.R;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//Reference: http://blog.csdn.net/sbsujjbcy/article/details/50716197

public class ConnectSpeedActivity extends AppCompatActivity {

  private TextView mTextView;
  private ConnectionChangedListener mListener = new ConnectionChangedListener();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_connect_speed);

    mTextView = (TextView) findViewById(R.id.connect_speed);
  }

  private class ConnectionChangedListener implements
      ConnectionClassManager.ConnectionClassStateChangeListener {
    @Override
    public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
      final ConnectionQuality connectionQuality = ConnectionClassManager
          .getInstance().getCurrentBandwidthQuality();
      final double downloadKBitsPerSecond = ConnectionClassManager
          .getInstance().getDownloadKBitsPerSecond();
      mTextView.post(new Runnable() {
        @Override
        public void run() {
          mTextView.setText("Connection Quality: " + connectionQuality
              + "\nDownloadKBitPerSecond: " + downloadKBitsPerSecond);
        }
      });
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    ConnectionClassManager.getInstance().register(mListener);
  }

  @Override
  protected void onPause() {
    super.onPause();
    ConnectionClassManager.getInstance().remove(mListener);
  }

  public void testSpeed(View view) {
    OkHttpClient client = new OkHttpClient();
    Request.Builder builder = new Request.Builder();
    Request request = builder.url("http://www.baidu.com").build();

    DeviceBandwidthSampler.getInstance().startSampling();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        DeviceBandwidthSampler.getInstance().stopSampling();
        Log.e("TAG", "onFailure: " + e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        DeviceBandwidthSampler.getInstance().stopSampling();
        Log.e("TAG", "OnResponse:" + response);
        final ConnectionQuality connectionQuality = ConnectionClassManager
            .getInstance().getCurrentBandwidthQuality();
        final double downloadKBitsPerSecond = ConnectionClassManager
            .getInstance().getDownloadKBitsPerSecond();
        mTextView.post(new Runnable() {
          @Override
          public void run() {
            mTextView.setText("Connection Quality: " + connectionQuality
                + "\nDownloadKBitPerSecond: " + downloadKBitsPerSecond);
          }
        });

      }
    });
  }
}
