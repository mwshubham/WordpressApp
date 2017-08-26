package com.techdevfan.wordpressapp.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.techdevfan.wordpressapp.helper.SharedPreferenceHelper;

import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_AD_ENABLED;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_HIDE_CATEGORY_WITH_NO_POST;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_SHOW_CUSTOM_PAGES;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_SHOW_MIN_READ_TIME;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_IS_TAGS_ENABLED;
import static com.techdevfan.wordpressapp.helper.SharedPreferenceHelper.KEY_POST_COUNT;

/**
 * Created by shubham on 26/7/17.
 */

public class ConfigData {
    @SuppressWarnings("unused")
    private static final String TAG = "ConfigData";

    @SerializedName("isAdEnabled")
    @Expose
    private boolean isAdEnabled;
    @SerializedName("isHideCategoryWithNoPost")
    @Expose
    private boolean isHideCategoryWithNoPost;
    @SerializedName("isTagsEnabled")
    @Expose
    private boolean isTagsEnabled;
    @SerializedName("isShowMinReadTime")
    @Expose
    private boolean isShowMinReadTime;
    @SerializedName("isShowCustomPages")
    @Expose
    private boolean isShowCustomPages;
    @SerializedName("postCount")
    @Expose
    private int postCount;


    @SuppressWarnings("WeakerAccess")
    public boolean isAdEnabled() {
        return isAdEnabled;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isHideCategoryWithNoPost() {
        return isHideCategoryWithNoPost;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isTagsEnabled() {
        return isTagsEnabled;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isShowMinReadTime() {
        return isShowMinReadTime;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isShowCustomPages() {
        return isShowCustomPages;
    }

    @SuppressWarnings("WeakerAccess")
    public int getPostCount() {
        return postCount;
    }

    public void updateSharedPrefData(Context context) {
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, KEY_IS_AD_ENABLED, isAdEnabled());
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, KEY_IS_HIDE_CATEGORY_WITH_NO_POST, isHideCategoryWithNoPost());
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, KEY_IS_TAGS_ENABLED, isTagsEnabled());
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, KEY_IS_SHOW_MIN_READ_TIME, isShowMinReadTime());
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, KEY_IS_SHOW_CUSTOM_PAGES, isShowCustomPages());
        SharedPreferenceHelper.setSharedPreferenceInt(context, KEY_POST_COUNT, getPostCount());
    }
}
