package com.techdevfan.wordpressapp.connection;

import android.content.Context;
import android.text.TextUtils;

import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.ConfigData;
import com.techdevfan.wordpressapp.model.TagData;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by shubham on 21/7/17.
 */

public class ApiConnection {
    @SuppressWarnings("unused")
    private static final String TAG = "ApiConnection";

    /*POST*/
    public static Observable<List<PostData>> getPosts(Context context, @NonNull String categoryId, @NonNull String tagId, int page) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getPosts(
                categoryId.isEmpty() ? null : categoryId
                , tagId.isEmpty() ? null : tagId
                , page
        );
    }

    public static Observable<PostData> getPost(Context context, @NonNull String postId) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getPost(postId);
    }

    /*CATEGORY*/
    public static Observable<List<CategoryData>> getCategories(Context context) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getCategories();
    }

    public static Observable<CategoryData> getCategory(Context context, @NonNull String categoryId) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getCategory(categoryId);
    }

    /*TAG*/
    public static Observable<List<TagData>> getTags(Context context) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getTags();
    }

    /*PAGE*/

    public static Observable<List<PostData>> getPages(Context context) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getPages();
    }

    /*CUSTOM ENDPOINTS*/
    public static Observable<ConfigData> getConfigData(Context context) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getConfigData();
    }

    public static Observable<List<PostData>> getFavoritePost(Context context, List<String> favoritePostIds) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getFavoritePost(TextUtils.join(",", favoritePostIds));
    }


}
