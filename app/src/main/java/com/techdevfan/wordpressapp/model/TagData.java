package com.techdevfan.wordpressapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.techdevfan.wordpressapp.database.contract.TagContract.TagEntry.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.contract.TagContract.TagEntry.COLUMN_NAME_NAME;

/**
 * Created by shubham on 27/7/17.
 */

public class TagData extends RealmObject {

    @SuppressWarnings("unused")
    private static final String TAG = "TagData";

    @PrimaryKey
    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

    @SerializedName(COLUMN_NAME_NAME)
    @Expose
    public String name;

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
