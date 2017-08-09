package com.techdevfan.wordpressapp.helper;

/**
 * Created by shubham on 27/7/17.
 */

public class EnumHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "EnumHelper";

    public enum IdType {
        CATEGORY_ID, TAG_ID
    }

    public enum NavItemType {
        TYPE_CATEGORY, TYPE_FAVORITE, TYPE_PAGE
    }
}
