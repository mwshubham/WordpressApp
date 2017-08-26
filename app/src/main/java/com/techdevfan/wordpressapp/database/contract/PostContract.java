package com.techdevfan.wordpressapp.database.contract;

import android.provider.BaseColumns;

@SuppressWarnings("unused")
public final class PostContract {
    @SuppressWarnings("unused")
    private static final String TAG = "PostContract";

    private PostContract() {
    }

    public static class PostEntry implements BaseColumns {
        public static final String TABLE_NAME = "post";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_EXCEPT = "excerpt";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_FEATURED_IMAGE_FULL = "featured_image_full";
        public static final String COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD = "featured_image_thumb_standard";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_LINK = "link";
    }
}
