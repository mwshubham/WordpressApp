package com.techdevfan.wordpressapp;

import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

/**
 * Created by shubham on 22/7/17.
 */

public class WordpressApplication extends MultiDexApplication {
    @SuppressWarnings("unused")
    private static final String TAG = "WordpressApplication";


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Realm.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        //        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
    }
}
