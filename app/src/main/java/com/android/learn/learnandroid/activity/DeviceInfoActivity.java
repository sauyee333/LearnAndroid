package com.android.learn.learnandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.android.learn.learnandroid.R;

public class DeviceInfoActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_device_info);

    TextView widthView = (TextView) findViewById(R.id.width);
    TextView heightView = (TextView) findViewById(R.id.height);
    TextView densityView = (TextView) findViewById(R.id.density);
    TextView typeView = (TextView) findViewById(R.id.type);
    TextView dpiView = (TextView) findViewById(R.id.dpi);

    DisplayMetrics dm = getResources().getDisplayMetrics();

    widthView.setText("Width: " + dm.widthPixels);
    heightView.setText("Height: " + dm.heightPixels);
    densityView.setText("Density: " + dm.densityDpi);
    switch (dm.densityDpi) {
    case DisplayMetrics.DENSITY_LOW:
      typeView.setText("Density: low");
      break;
    case DisplayMetrics.DENSITY_MEDIUM:
      typeView.setText("Density: medium");
      break;
    case DisplayMetrics.DENSITY_HIGH:
      typeView.setText("Density: high");
      break;
    case DisplayMetrics.DENSITY_TV:
      typeView.setText("Density: tv");
      break;
    case DisplayMetrics.DENSITY_XHIGH:
      typeView.setText("Density: xhigh");
      break;
    case DisplayMetrics.DENSITY_XXHIGH:
      typeView.setText("Density: xxhigh");
      break;
    case DisplayMetrics.DENSITY_XXXHIGH:
      typeView.setText("Density: xxxhigh");
      break;
    default:
      typeView.setText("Density: other");
      break;
    }

    if (dm.density == 0.75) {
      dpiView.setText("Dpi: ldpi");
    } else if (dm.density == 1.0) {
      dpiView.setText("Dpi: mdpi");
    } else if (dm.density == 1.5) {
      dpiView.setText("Dpi: hdpi");
    } else if (dm.density == 2.0) {
      dpiView.setText("Dpi: xhdpi");
    } else if (dm.density == 3.0) {
      dpiView.setText("Dpi: xxhdpi");
    } else if (dm.density == 4.0) {
      dpiView.setText("Dpi: xxxhdpi");
    }

  }
}
