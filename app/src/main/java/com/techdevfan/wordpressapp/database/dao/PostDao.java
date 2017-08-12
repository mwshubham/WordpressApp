package com.techdevfan.wordpressapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_TYPE;
import static com.techdevfan.wordpressapp.database.AppDatabase.POST_TABLE_NAME;

/**
 * Created by shubham on 1/8/17.
 */
@Dao
public interface PostDao {

    @Query("SELECT * FROM " + POST_TABLE_NAME)
    List<PostData> getAll();


    @Query("SELECT * FROM " + POST_TABLE_NAME + " WHERE " + COLUMN_NAME_TYPE + " = :type")
    List<PostData> getAllPost(String type);


    /**
     * @param id aka postId id of the post
     * @return PostData for the requested post id
     */
    @Query("SELECT * FROM " + POST_TABLE_NAME + " WHERE " + COLUMN_NAME_ID + " = :id")
    PostData getPost(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PostData> postDatas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostData postData);

    @Delete
    void delete(PostData postData);
}
