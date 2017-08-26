package com.techdevfan.wordpressapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.techdevfan.wordpressapp.database.contract.FavoriteContract.FavoriteEntry.COLUMN_NAME_POST_ID;

/**
 * Created by shubham on 31/7/17.
 */
public class FavoriteData {
    @SuppressWarnings("unused")
    private static final String TAG = "FavoriteData";

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
