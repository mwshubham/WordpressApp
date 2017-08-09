package com.techdevfan.wordpressapp.connection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.techdevfan.wordpressapp.constant.ApplicationConstant.BASE_URL;
import static com.techdevfan.wordpressapp.constant.ApplicationConstant.DEFAULT_CONNECT_TIMEOUT_SEC;
import static com.techdevfan.wordpressapp.constant.ApplicationConstant.DEFAULT_READ_TIMEOUT_SEC;
import static com.techdevfan.wordpressapp.constant.ApplicationConstant.MAX_RETRY_COUNT;

/**
 * Created by shubham on 21/7/17.
 */

public class RetrofitClient {
    @SuppressWarnings("unused")
    private static final String TAG = "RetrofitClient";

    private static Retrofit sRetrofit = null;
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public static Retrofit getClient(Context context) {
        if (sRetrofit == null) {
            sContext = context.getApplicationContext(); // in case you need it
            sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // to support Refrofit2 via RxAndroid
                    //  .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).client(getOkHttpClientBuilder().build()).build();
        }
        return sRetrofit;
    }

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.readTimeout(DEFAULT_READ_TIMEOUT_SEC, TimeUnit.SECONDS).connectTimeout(DEFAULT_CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS);


        okHttpClientBuilder.interceptors().add(chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int tryCount = 0;
            while (!response.isSuccessful() && tryCount < MAX_RETRY_COUNT) {
                Log.i(TAG, "Request is not successful Attempt #" + tryCount);
                tryCount++;
                response = chain.proceed(request);
            }
            return response;
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);


        return okHttpClientBuilder;
    }
}
