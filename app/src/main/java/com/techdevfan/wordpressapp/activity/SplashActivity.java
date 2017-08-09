package com.techdevfan.wordpressapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.connection.ApiConnection;
import com.techdevfan.wordpressapp.connection.CustomObserver;
import com.techdevfan.wordpressapp.database.AppDatabase;
import com.techdevfan.wordpressapp.helper.SharedPreferenceHelper;
import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.ConfigData;
import com.techdevfan.wordpressapp.model.TagData;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shubham on 21/7/17.
 */

public class SplashActivity extends BaseActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "SplashActivity";
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Log.i(TAG, "token: " + FirebaseInstanceId.getInstance().getToken());
        ApiConnection.getConfigData(this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<ConfigData>(this) {
            @Override
            public void onNext(@NonNull ConfigData configData) {
                super.onNext(configData);
                configData.updateSharedPrefData(SplashActivity.this);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                loadCategoryNTagData();
            }
        });
//        throw new RuntimeException();
//        loadCategoryNTagData();
    }


    private void loadCategoryNTagData() {
        Observable<List<TagData>> tagListObservable = ApiConnection.getTags(this).subscribeOn(Schedulers.io());
        Observable<List<CategoryData>> categoryListObservable = ApiConnection.getCategories(this).subscribeOn(Schedulers.io());
        Observable.zip(tagListObservable, categoryListObservable, (tagDatas, categoryDatas) -> {

            for (CategoryData eachCategoryData : categoryDatas) {
                if ((SharedPreferenceHelper.getSharedPreferenceBoolean(SplashActivity.this, SharedPreferenceHelper.KEY_IS_HIDE_CATEGORY_WITH_NO_POST, false) && eachCategoryData.getCountInt() == 0)) {
                    continue;
                }
                AppDatabase.getAppDatabase(this).getCategoryDao().insert(eachCategoryData);
            }

            for (TagData eachTagData : tagDatas) {
                if ((SharedPreferenceHelper.getSharedPreferenceBoolean(SplashActivity.this, SharedPreferenceHelper.KEY_IS_TAGS_ENABLED, false))) {
                    AppDatabase.getAppDatabase(this).getTagDao().insert(eachTagData);
                }
            }

            return true;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<Object>(this) {
            @Override
            public void onComplete() {
                super.onComplete();
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.msg_press_back_again_to_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
