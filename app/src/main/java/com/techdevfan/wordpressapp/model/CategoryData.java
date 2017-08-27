package com.techdevfan.wordpressapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_COUNT;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_DESCRIPTION;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.contract.CategoryContract.CategoryEntry.COLUMN_NAME_NAME;

/**
 * Created by shubham on 22/7/17.
 */
public class CategoryData extends RealmObject {
    @SuppressWarnings("unused")
    private static final String TAG = "CategoryData";

    @PrimaryKey
    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

    @SerializedName(COLUMN_NAME_COUNT)
    @Expose
    public String count;

    @SerializedName(COLUMN_NAME_DESCRIPTION)
    @Expose
    public String description;

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
