package com.android.learn.learnandroid;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;

public class MyApplication extends Application {
  public void onCreate() {
    super.onCreate();

    // Stetho.initializeWithDefaults(this);
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());

    // Fabric.with(this, new Crashlytics());
    final Fabric fabric = new Fabric.Builder(this).kits(new Crashlytics())
        .debuggable(true).build();
    Fabric.with(fabric);

    LeakCanary.install(this);
  }
}
