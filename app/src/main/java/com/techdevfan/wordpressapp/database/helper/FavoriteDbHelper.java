package com.techdevfan.wordpressapp.database.helper;

import android.content.Context;

/**
 * Created by shubham on 26/8/17.
 */

@SuppressWarnings("unused")
public class FavoriteDbHelper extends BaseSQLliteOpenHelper {
    private static final String TAG = "FavoriteDbHelper";
    private static FavoriteDbHelper sInstance;

    private FavoriteDbHelper(Context context) {
        super(context);
    }

    public static synchronized FavoriteDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new FavoriteDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }


//    public boolean isFavoritePost(Context context, String postId) {
//        List<FavoriteData> favoriteDatas = getAppDatabase(context).getFavoriteDao().loadFavoritePost(postId);
//        return favoriteDatas.size() > 0;
//    }
}
