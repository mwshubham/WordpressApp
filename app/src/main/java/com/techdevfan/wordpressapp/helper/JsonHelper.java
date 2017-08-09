package com.techdevfan.wordpressapp.helper;

import android.content.Context;

import com.techdevfan.wordpressapp.activity.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.annotations.NonNull;

/**
 * Created by shubham on 26/7/17.
 */

public class JsonHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "JsonHelper";

    @NonNull
    public static String loadJSONFromAsset(Context context, String filePath) {
        String json = "";
        try {
            InputStream is = ((BaseActivity) context).getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
        return json;
    }
}
