package com.android.learn.learnandroid;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {
  public void onCreate() {
    super.onCreate();

    // Stetho.initializeWithDefaults(this);
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
  }
}
