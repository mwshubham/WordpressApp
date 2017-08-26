package com.techdevfan.wordpressapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 22/7/17.
 * <p>
 * a short extract of a Post
 */

public class ExcerptData {
    @SuppressWarnings("unused")
    private static final String TAG = "ExcerptData";

    @SerializedName("rendered")
    @Expose
    public String rendered;

    public ExcerptData(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        if (rendered == null) {
            return "";
        }
        return rendered;
    }
}
