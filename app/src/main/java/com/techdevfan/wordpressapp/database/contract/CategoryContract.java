package com.techdevfan.wordpressapp.database.contract;

import android.provider.BaseColumns;

public final class CategoryContract {

    @SuppressWarnings("unused")
    private static final String TAG = "CategoryContract";

    private CategoryContract() {
    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COUNT = "count";
    }
}
