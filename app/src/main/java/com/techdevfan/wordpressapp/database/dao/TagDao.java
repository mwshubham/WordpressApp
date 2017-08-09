package com.techdevfan.wordpressapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.techdevfan.wordpressapp.database.AppDatabase;
import com.techdevfan.wordpressapp.model.TagData;

import java.util.List;

import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_ID;

/**
 * Created by shubham on 31/7/17.
 */
@Dao
public interface TagDao {

    @Query("SELECT * FROM " + AppDatabase.TAG_TABLE_NAME)
    List<TagData> getAll();

    @Query("SELECT * FROM " + AppDatabase.TAG_TABLE_NAME + " WHERE " + COLUMN_NAME_ID + " IN (:tagIds)")
    List<TagData> getTagDatas(List<String> tagIds);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TagData> tagDatas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TagData tagData);

    @Delete
    void delete(TagData tagData);
}
