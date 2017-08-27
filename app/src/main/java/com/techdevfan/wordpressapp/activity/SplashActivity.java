package com.techdevfan.wordpressapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.connection.ApiConnection;
import com.techdevfan.wordpressapp.connection.CustomObserver;
import com.techdevfan.wordpressapp.helper.SharedPreferenceHelper;
import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.ConfigData;
import com.techdevfan.wordpressapp.model.TagData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.HttpException;
import retrofit2.Response;

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
            public void onSubscribe(@NonNull Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull ConfigData configData) {
                super.onNext(configData);
                configData.updateSharedPrefData(SplashActivity.this);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    Response response = httpException.response();
                    /*HANDLING ERROR : {"code":"rest_no_route","message":"No route was found matching the URL and request method","data":{"status":404}}*/
                    if (response.code() == 404) {
                        onComplete();
                    } else {
                        super.onError(e);
                    }
                } else {
                    super.onError(e);
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
                loadCategoryNTagData();
            }
        });
    }


    private void loadCategoryNTagData() {

        Observable<List<TagData>> tagListObservable = ApiConnection.getTags(this).subscribeOn(Schedulers.io());
        Observable<List<CategoryData>> categoryListObservable = ApiConnection.getCategories(this).subscribeOn(Schedulers.io());
        Observable.zip(tagListObservable, categoryListObservable, (List<TagData> tagDatas, List<CategoryData> categoryDatas) -> {
            for (CategoryData eachCategoryData : categoryDatas) {
                if ((SharedPreferenceHelper.getSharedPreferenceBoolean(SplashActivity.this, SharedPreferenceHelper.KEY_IS_HIDE_CATEGORY_WITH_NO_POST, false) && eachCategoryData.getCountInt() == 0)) {
                    continue;
                }
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> realm1.insertOrUpdate(eachCategoryData));
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }

            for (TagData eachTagData : tagDatas) {
                if ((SharedPreferenceHelper.getSharedPreferenceBoolean(SplashActivity.this, SharedPreferenceHelper.KEY_IS_TAGS_ENABLED, false))) {
                    Realm realm = null;
                    try {
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(eachTagData));
                    } finally {
                        if (realm != null) {
                            realm.close();
                        }
                    }
                }
            }
            return true;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<Object>(this) {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mCompositeDisposable.add(d);
            }

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
        Toast.makeText(this, R.string.press_back_again_to_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

}
