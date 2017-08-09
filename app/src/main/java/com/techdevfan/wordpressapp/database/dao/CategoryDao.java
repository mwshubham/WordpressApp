package com.techdevfan.wordpressapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.techdevfan.wordpressapp.database.AppDatabase;
import com.techdevfan.wordpressapp.model.CategoryData;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE_NAME)
    List<CategoryData> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CategoryData> categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryData categoryData);

    @Delete
    void delete(CategoryData category);
}