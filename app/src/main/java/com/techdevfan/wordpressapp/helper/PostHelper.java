package com.techdevfan.wordpressapp.helper;

import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 11/8/17.
 */

public class PostHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "PostHelper";

    public static List<PostData> getPosts(List<PostData> postDatas, String categoryId) {
        List<PostData> filteredPostDatas = new ArrayList<>();
        for (PostData eachPostData : postDatas) {
            if (eachPostData.getCategories().contains(categoryId)) {
                filteredPostDatas.add(eachPostData);
            }

        }
        return filteredPostDatas;
    }

    public static List<PostData> getPostsByTag(List<PostData> postDatas, String tagId) {
        List<PostData> filteredPostDatas = new ArrayList<>();
        for (PostData eachPostData : postDatas) {
            if (eachPostData.getTags().contains(tagId)) {
                filteredPostDatas.add(eachPostData);
            }

        }
        return filteredPostDatas;
    }
}
