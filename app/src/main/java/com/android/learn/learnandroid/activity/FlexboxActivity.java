package com.android.learn.learnandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.learn.learnandroid.R;

public class FlexboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);
    }

    private static void _Debug(String str) {
        Log.d("widget", str);
    }
}
