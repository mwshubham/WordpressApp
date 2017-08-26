package com.techdevfan.wordpressapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.techdevfan.wordpressapp.database.contract.TagContract.TagEntry.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.contract.TagContract.TagEntry.COLUMN_NAME_NAME;

/**
 * Created by shubham on 27/7/17.
 */

public class TagData {

    @SuppressWarnings("unused")
    private static final String TAG = "TagData";

    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

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
