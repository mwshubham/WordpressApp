package com.techdevfan.wordpressapp.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_AUTHOR;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_CONTENT;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_DATE;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_EXCEPT;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_FEATURED_IMAGE_FULL;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_LINK;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_TITLE;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.COLUMN_NAME_TYPE;
import static com.techdevfan.wordpressapp.database.contract.PostContract.PostEntry.TABLE_NAME;

/**
 * Created by shubham on 26/8/17.
 */

public class PostDbHelper extends BaseSQLliteOpenHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "PostDbHelper";
    private static PostDbHelper sInstance;

    private PostDbHelper(Context context) {
        super(context);
    }


    public static synchronized PostDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PostDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public void insertAll(List<PostData> postDataList) {
        Observable.fromCallable(() -> {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            for (PostData postData : postDataList) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME_ID, postData.getId());
                values.put(COLUMN_NAME_DATE, postData.getDate());
                values.put(COLUMN_NAME_TITLE, postData.getTitle().getRendered());
                values.put(COLUMN_NAME_CONTENT, postData.getContent().getRendered());

                values.put(COLUMN_NAME_EXCEPT, postData.getExcerpt().getRendered());
                values.put(COLUMN_NAME_AUTHOR, postData.getAuthor());
                values.put(COLUMN_NAME_FEATURED_IMAGE_FULL, postData.getFeaturedImageFull());
                values.put(COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD, postData.getFeaturedImageThumbnailStandard());
                values.put(COLUMN_NAME_TYPE, postData.getType());
//                values.put(COLUMN_NAME_STATUS, postData.getType());
                values.put(COLUMN_NAME_LINK, postData.getLink());


                sqLiteDatabase.insert(TABLE_NAME, null, values);
            }
            close();
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

//    public synchronized List<PostData> getAllPost(String typePost) {
//        SQLiteDatabase readableDatabase = getReadableDatabase();
//        Cursor cursor = null;
//        List<PostData> postDataList = new ArrayList<>();
//        try {
//            cursor = readableDatabase.query(TABLE_NAME
//                    , new String[]{
//                            COLUMN_NAME_ID
//                            , COLUMN_NAME_DATE
//                            , COLUMN_NAME_TITLE
//                            , COLUMN_NAME_CONTENT
//                            , COLUMN_NAME_EXCEPT
//                            , COLUMN_NAME_AUTHOR
//                            , COLUMN_NAME_FEATURED_IMAGE_FULL
//                            , COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD
//                            , COLUMN_NAME_TYPE
//                            , COLUMN_NAME_LINK
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
//                        postDataList.add(
//                                new PostData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE))
//                                        , new TitleData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)))
//                                        , new ContentData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CONTENT)))
//                                        , new ExcerptData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_AUTHOR))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FEATURED_IMAGE_FULL))
//                                        , cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD))
//                                        , null
//                                        , null
//
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
//        return postDataList;
//    }
}
