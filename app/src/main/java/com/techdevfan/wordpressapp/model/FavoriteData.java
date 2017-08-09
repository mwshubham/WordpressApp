package com.techdevfan.wordpressapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_POST_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.FAVORITE_TABLE_NAME;

/**
 * Created by shubham on 31/7/17.
 */
@Entity(tableName = FAVORITE_TABLE_NAME)
public class FavoriteData {
    @SuppressWarnings("unused")
    private static final String TAG = "FavoriteData";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_POST_ID)
    @SerializedName(COLUMN_NAME_POST_ID)
    @Expose
    public String postId;

    public FavoriteData(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        if (postId == null) {
            return "";
        }
        return postId;
    }


}
