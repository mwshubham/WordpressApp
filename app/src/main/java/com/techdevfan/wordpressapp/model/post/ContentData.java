package com.techdevfan.wordpressapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 22/7/17.
 */

public class ContentData {
    @SuppressWarnings("unused")
    private static final String TAG = "ContentData";

    @SerializedName("rendered")
    @Expose
    public String rendered;

    public ContentData(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        if (rendered == null) {
            return "";
        }
        return rendered;
    }
}


