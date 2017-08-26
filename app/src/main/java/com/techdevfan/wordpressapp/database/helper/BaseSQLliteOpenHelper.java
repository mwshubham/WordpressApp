package com.techdevfan.wordpressapp.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.techdevfan.wordpressapp.database.contract.CategoryContract;
import com.techdevfan.wordpressapp.database.contract.PostContract;
import com.techdevfan.wordpressapp.database.contract.TagContract;

/**
 * Created by shubham on 26/8/17.
 */

public class BaseSQLliteOpenHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = "BaseSQLliteOpenHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WordpressApp.db";

    private static final String TAG_CREATE_ENTRIES =
            "CREATE TABLE " + TagContract.TagEntry.TABLE_NAME + " (" +
                    TagContract.TagEntry._ID + " INTEGER PRIMARY KEY," +
                    TagContract.TagEntry.COLUMN_NAME_ID + " TEXT," +
                    TagContract.TagEntry.COLUMN_NAME_NAME + " TEXT," +
                    "UNIQUE (" + TagContract.TagEntry.COLUMN_NAME_ID + ") ON CONFLICT REPLACE)";


    private static final String CATEGORY_CREATE_ENTRIES =
            "CREATE TABLE " + CategoryContract.CategoryEntry.TABLE_NAME + " (" +
                    CategoryContract.CategoryEntry._ID + " INTEGER PRIMARY KEY," +
                    CategoryContract.CategoryEntry.COLUMN_NAME_ID + " TEXT," +
                    CategoryContract.CategoryEntry.COLUMN_NAME_NAME + " TEXT," +
                    CategoryContract.CategoryEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    CategoryContract.CategoryEntry.COLUMN_NAME_COUNT + " TEXT," +
                    "UNIQUE (" + CategoryContract.CategoryEntry.COLUMN_NAME_ID + ") ON CONFLICT REPLACE)";

    private static final String POST_CREATE_ENTRIES =
            "CREATE TABLE " + PostContract.PostEntry.TABLE_NAME + " (" +
                    PostContract.PostEntry._ID + " INTEGER PRIMARY KEY," +
                    PostContract.PostEntry.COLUMN_NAME_ID + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_DATE + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_TITLE + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_CONTENT + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_EXCEPT + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_FEATURED_IMAGE_FULL + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_TYPE + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_STATUS + " TEXT," +
                    PostContract.PostEntry.COLUMN_NAME_LINK + " TEXT," +
                    "UNIQUE (" + PostContract.PostEntry.COLUMN_NAME_ID + ") ON CONFLICT REPLACE)";


    private static final String TAG_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TagContract.TagEntry.TABLE_NAME;
    private static final String CATEGORY_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CategoryContract.CategoryEntry.TABLE_NAME;
    private static final String POST_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PostContract.PostEntry.TABLE_NAME;

    BaseSQLliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAG_CREATE_ENTRIES);
        db.execSQL(CATEGORY_CREATE_ENTRIES);
        db.execSQL(POST_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TAG_DELETE_ENTRIES);
        db.execSQL(CATEGORY_DELETE_ENTRIES);
        db.execSQL(POST_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
