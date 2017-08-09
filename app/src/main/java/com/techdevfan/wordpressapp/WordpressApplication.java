package com.techdevfan.wordpressapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by shubham on 22/7/17.
 */

public class WordpressApplication extends Application {
    @SuppressWarnings("unused")
    private static final String TAG = "WordpressApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this);
    }
}
