package com.techdevfan.wordpressapp.connection;

import android.content.Context;

import com.techdevfan.wordpressapp.helper.NetworkHelper;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by shubham on 23/8/17.
 */

public class CustomCompletableObserver implements CompletableObserver {
    @SuppressWarnings("unused")
    private static final String TAG = "CustomCompletableObserv";
    private final Context mContext;

    public CustomCompletableObserver(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        NetworkHelper.onError(mContext, e);
    }

    @Override
    public void onComplete() {
    }

}
