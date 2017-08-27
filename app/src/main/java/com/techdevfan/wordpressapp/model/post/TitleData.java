package com.techdevfan.wordpressapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by shubham on 22/7/17.
 */

public class TitleData extends RealmObject {
    @SuppressWarnings("unused")
    private static final String TAG = "TitleData";

    @SerializedName("rendered")
    @Expose
    public String rendered;

    public String getRendered() {
        if (rendered == null) {
            return "";
        }
        return rendered;
    }
}
