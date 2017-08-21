package com.techdevfan.wordpressapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.techdevfan.wordpressapp.database.dao.CategoryDao;
import com.techdevfan.wordpressapp.database.dao.FavoriteDao;
import com.techdevfan.wordpressapp.database.dao.PostDao;
import com.techdevfan.wordpressapp.database.dao.TagDao;
import com.techdevfan.wordpressapp.database.typeadapter.Converters;
import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.FavoriteData;
import com.techdevfan.wordpressapp.model.TagData;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import static com.techdevfan.wordpressapp.database.AppDatabase.DATABASE_VERSION;

@Database(entities = {CategoryData.class, FavoriteData.class, TagData.class, PostData.class}, version = DATABASE_VERSION)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    /*Database Constant*/
    @SuppressWarnings("WeakerAccess")
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "WordpressApp.db";

    /*TABLE NAMES*/
    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String FAVORITE_TABLE_NAME = "favorite";
    public static final String TAG_TABLE_NAME = "tag";
    public static final String POST_TABLE_NAME = "post";
    /*DEFAULT KEYS*/
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_POST_ID = "postId";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_COUNT = "count";
    /*POST DATA KEYS*/
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CONTENT = "content";
    public static final String COLUMN_NAME_EXCEPT = "excerpt";
    public static final String COLUMN_NAME_AUTHOR = "author";
    public static final String COLUMN_NAME_LINK = "link";
    public static final String COLUMN_NAME_FEATURED_IMAGE_FULL = "featured_image_full";
    public static final String COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD = "featured_image_thumb_standard";
    public static final String COLUMN_NAME_CATEGORIES = "categories";
    public static final String COLUMN_NAME_TAGS = "tags";
    public static final String COLUMN_NAME_RENDERED = "rendered";
    private static final String TAG = "AppDatabase";

    public abstract CategoryDao getCategoryDao();

    public abstract FavoriteDao getFavoriteDao();

    public abstract TagDao getTagDao();

    public abstract PostDao getPostDao();

    public static AppDatabase getAppDatabase(Context context) {
        Log.i(TAG, "getAppDatabase: ");
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            // fixme Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public boolean isFavoritePost(Context context, String postId) {
        List<FavoriteData> favoriteDatas = getAppDatabase(context).getFavoriteDao().loadFavoritePost(postId);
        return favoriteDatas.size() > 0;
    }

}