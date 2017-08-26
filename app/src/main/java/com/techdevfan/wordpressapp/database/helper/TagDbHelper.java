package com.techdevfan.wordpressapp.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.techdevfan.wordpressapp.database.contract.TagContract;
import com.techdevfan.wordpressapp.model.TagData;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shubham on 26/8/17.
 */

@SuppressWarnings("unused")
public class TagDbHelper extends BaseSQLliteOpenHelper {

    private static final String TAG = "TagDbHelper";
    private static TagDbHelper sInstance;

    private TagDbHelper(Context context) {
        super(context);
    }

    public static synchronized TagDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TagDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public synchronized void insert(TagData tagData) {
        Observable.fromCallable(() -> {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TagContract.TagEntry.COLUMN_NAME_ID, tagData.getId());
            values.put(TagContract.TagEntry.COLUMN_NAME_NAME, tagData.getName());
            sqLiteDatabase.insert(TagContract.TagEntry.TABLE_NAME, null, values);
            close();
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
