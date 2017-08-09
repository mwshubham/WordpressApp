package com.techdevfan.wordpressapp.connection;


import android.content.Context;

import com.techdevfan.wordpressapp.activity.BaseActivity;
import com.techdevfan.wordpressapp.helper.NetworkHelper;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by shubham on 22/7/17.
 */

public class CustomObserver<T> implements Observer<T> {
    @SuppressWarnings("unused")
    private static final String TAG = "CustomObserver";
    private Context mContext;

    public CustomObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        ((BaseActivity) mContext).mCompositeDisposable.add(d);
    }

    @Override
    public void onNext(@NonNull T t) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        NetworkHelper.onError(mContext, e);
    }

    @Override
    public void onComplete() {
    }
}
