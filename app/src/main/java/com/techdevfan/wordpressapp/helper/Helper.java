package com.techdevfan.wordpressapp.helper;

import android.text.Html;
import android.text.Spanned;

import io.reactivex.annotations.NonNull;

/**
 * Created by shubham on 25/7/17.
 */

public class Helper {
    @SuppressWarnings("unused")
    private static final String TAG = "Helper";

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public static String initCap(@NonNull String title) {
        try {
            return title.substring(0, 1).toUpperCase() + title.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }


}


