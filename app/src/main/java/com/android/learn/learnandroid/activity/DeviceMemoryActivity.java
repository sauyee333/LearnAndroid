package com.android.learn.learnandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.learn.learnandroid.R;
import com.android.learn.learnandroid.model.StorageInfo;
import com.android.learn.learnandroid.util.SysUtil;

public class DeviceMemoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        TextView widthView = (TextView) findViewById(R.id.width);
        TextView heightView = (TextView) findViewById(R.id.height);
        TextView densityView = (TextView) findViewById(R.id.density);
        TextView typeView = (TextView) findViewById(R.id.type);
        TextView dpiView = (TextView) findViewById(R.id.dpi);

        StorageInfo sdInfo = SysUtil.getSDExternalStorageSize();
        if (sdInfo != null) {
            int sdTotal = SysUtil.convertByteToNearestMB(sdInfo.getTotalBytes());
            int sdFree = SysUtil.convertByteToNearestMB(sdInfo.getAvailBytes());
            widthView.setText("SD External Free: " + sdFree + "MB");
            heightView.setText("SD External Total: " + sdTotal + "MB");
        }

        StorageInfo internalInfo = SysUtil.getInternalStorageSize();
        if (internalInfo != null) {
            int internalTotal = SysUtil.convertByteToNearestMB(internalInfo.getTotalBytes());
            int internalFree = SysUtil.convertByteToNearestMB(internalInfo.getAvailBytes());
            densityView.setText("Internal Storage Free: " + internalFree + "MB");
            typeView.setText("Internal StorageTotal: " + internalTotal + "MB");
        }
    }
}
