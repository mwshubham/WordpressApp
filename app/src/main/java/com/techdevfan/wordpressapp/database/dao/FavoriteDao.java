package com.techdevfan.wordpressapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.techdevfan.wordpressapp.model.FavoriteData;

import java.util.List;

import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_POST_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.FAVORITE_TABLE_NAME;

/**
 * Created by shubham on 31/7/17.
 */
@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM " + FAVORITE_TABLE_NAME)
    List<FavoriteData> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FavoriteData> favoriteDatas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteData favoriteData);

    @Delete
    void delete(FavoriteData favoriteData);

    @Query("SELECT * FROM " + FAVORITE_TABLE_NAME + " WHERE " + COLUMN_NAME_POST_ID + " = :postId")
    List<FavoriteData> loadFavoritePost(String postId);

}
