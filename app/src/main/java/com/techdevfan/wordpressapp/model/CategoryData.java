package com.techdevfan.wordpressapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.techdevfan.wordpressapp.database.AppDatabase.CATEGORY_TABLE_NAME;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_COUNT;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_DESCRIPTION;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_NAME;

/**
 * Created by shubham on 22/7/17.
 */
@Entity(tableName = CATEGORY_TABLE_NAME)
public class CategoryData {
    @SuppressWarnings("unused")
    private static final String TAG = "CategoryData";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_ID)
    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

    @ColumnInfo(name = COLUMN_NAME_COUNT)
    @SerializedName(COLUMN_NAME_COUNT)
    @Expose
    public String count;

    @ColumnInfo(name = COLUMN_NAME_DESCRIPTION)
    @SerializedName(COLUMN_NAME_DESCRIPTION)
    @Expose
    public String description;

    @ColumnInfo(name = COLUMN_NAME_NAME)
    @SerializedName(COLUMN_NAME_NAME)
    @Expose
    public String name;

    public String getId() {
        if (id == null) {
            return "";
        }
        return id;
    }

    public String getCount() {
        if (count == null) {
            return "";
        }
        return count;
    }

    public int getCountInt() {
        if (getCount() == null || getCount().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(getCount());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getDescription() {
        if (description == null) {
            return "";
        }

        return description;
    }

    public String getName() {
        if (name == null) {
            return "";
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
