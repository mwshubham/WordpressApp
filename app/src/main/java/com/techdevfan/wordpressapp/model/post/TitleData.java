package com.techdevfan.wordpressapp.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 22/7/17.
 */

public class TitleData {
    @SuppressWarnings("unused")
    private static final String TAG = "TitleData";

    @SerializedName("rendered")
    @Expose
    public String rendered;

    public TitleData(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        if (rendered == null) {
            return "";
        }
        return rendered;
    }
}
