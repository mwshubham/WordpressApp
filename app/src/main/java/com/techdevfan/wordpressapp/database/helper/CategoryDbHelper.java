package com.techdevfan.wordpressapp.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.techdevfan.wordpressapp.model.CategoryData;

import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_COUNT;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_DESCRIPTION;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_NAME;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.TABLE_NAME;

/**
 * Created by shubham on 26/8/17.
 */

public class CategoryDbHelper extends BaseSQLliteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = "CategoryDbHelper";
    private static CategoryDbHelper sInstance;

    private CategoryDbHelper(Context context) {
        super(context);
    }

    public static synchronized CategoryDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CategoryDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public void insert(CategoryData categoryData) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, categoryData.getId());
        values.put(COLUMN_NAME_NAME, categoryData.getName());
        values.put(COLUMN_NAME_DESCRIPTION, categoryData.getDescription());
        values.put(COLUMN_NAME_COUNT, categoryData.getCount());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        close();
    }


//    public synchronized List<CategoryData> getAll() {
//        SQLiteDatabase readableDatabase = getReadableDatabase();
//        Cursor cursor = null;
//        List<CategoryData> categoryDataList = new ArrayList<>();
//        try {
//            cursor = readableDatabase.query(TABLE_NAME
//                    , new String[]{
//                            COLUMN_NAME_ID
//                            , COLUMN_NAME_NAME
//                            , COLUMN_NAME_DESCRIPTION
//                            , COLUMN_NAME_COUNT
//                    }
//                    , null
//                    , null
//                    , null
//                    , null
//                    , null);
//
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        categoryDataList.add(
//                                new CategoryData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COUNT))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME))
//                                ));
//                    } while (cursor.moveToNext());
//                }
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            close();
//        }
//        return categoryDataList;
//    }

}
