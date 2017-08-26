package com.techdevfan.wordpressapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;
import com.techdevfan.wordpressapp.helper.SharedPreferenceHelper;

import io.fabric.sdk.android.Fabric;
import io.reactivex.disposables.CompositeDisposable;

import static com.techdevfan.wordpressapp.constant.ApplicationConstant.ADMOB_APP_ID;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_AD_ENABLED;

/**
 * Created by shubham on 21/7/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "BaseActivity";

    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        if (SharedPreferenceHelper.getSharedPreferenceBoolean(this, KEY_IS_AD_ENABLED, false)) {
            MobileAds.initialize(this, ADMOB_APP_ID);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        mCompositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
