package com.techdevfan.wordpressapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_NAME;
import static com.techdevfan.wordpressapp.database.AppDatabase.TAG_TABLE_NAME;

/**
 * Created by shubham on 27/7/17.
 */

@Entity(tableName = TAG_TABLE_NAME)
public class TagData {

    @SuppressWarnings("unused")
    private static final String TAG = "TagData";


    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_ID)
    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

    @ColumnInfo(name = COLUMN_NAME_NAME)
    @SerializedName(COLUMN_NAME_NAME)
    @Expose
    public String name;

    public TagData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        if (id == null) {
            return "";
        }
        return id;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }
}
