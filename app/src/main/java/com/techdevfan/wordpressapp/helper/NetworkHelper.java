package com.techdevfan.wordpressapp.helper;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by shubham on 24/7/17.
 */

public class NetworkHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "NetworkHelper";


//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }

    public static void onError(Context context, Throwable e) {
        Log.d(TAG, "onError: ");
        e.printStackTrace();

        try {


//            if (isNetworkAvailable(context)) {
//            } else {
//            }
            // We had non-200 http error
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                Response response = httpException.response();
                Log.i(TAG, "onError: " + response.code());
                Log.i(TAG, e.getMessage() + " / " + e.getClass());
            }
            // A e error happened
            if (e instanceof IOException) {
                Log.i(TAG, e.getMessage() + " / " + e.getClass());
            }

            Log.i(TAG, e.getMessage() + " / " + e.getClass());
        } catch (Exception ex) {
            Log.i(TAG, ex.getMessage());
        }
    }
}
