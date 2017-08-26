package com.techdevfan.wordpressapp.database.contract;

import android.provider.BaseColumns;

/**
 * Created by shubham on 26/8/17.
 */

public final class FavoriteContract {

    @SuppressWarnings("unused")
    private static final String TAG = "FavoriteContract";

    private FavoriteContract() {
    }

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_POST_ID = "postId";
    }


}
