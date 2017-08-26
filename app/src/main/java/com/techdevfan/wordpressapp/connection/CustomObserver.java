package com.techdevfan.wordpressapp.connection;


import android.content.Context;

import com.techdevfan.wordpressapp.helper.NetworkHelper;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by shubham on 22/7/17.
 */

public abstract class CustomObserver<T> implements Observer<T> {
    @SuppressWarnings("unused")
    private static final String TAG = "CustomObserver";
    private Context mContext;

    public CustomObserver(Context context) {
        mContext = context.getApplicationContext();
    }

    /*cannot pass context of the activity i.e. to prevent memory leak*/
//    @Override
//    public void onSubscribe(@NonNull Disposable d) {
//        ((BaseActivity) mContext).mCompositeDisposable.add(d);
//    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        NetworkHelper.onError(mContext, e);
    }

    @Override
    public void onComplete() {
    }
}
