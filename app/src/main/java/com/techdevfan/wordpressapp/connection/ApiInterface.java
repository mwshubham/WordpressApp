package com.techdevfan.wordpressapp.connection;

import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.ConfigData;
import com.techdevfan.wordpressapp.model.TagData;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shubham on 21/7/17.
 */

@SuppressWarnings({"WeakerAccess", "DefaultFileTemplate"})
public interface ApiInterface {

    /* on adding any field herem you need to update the get_favorite_post_data function as it also return the post data for favorite post*/
    String FIELDS_POST = "?fields=id,date,title,content,excerpt,author,featured_image_full,featured_image_thumb_standard,categories,tags,type,status,link";
    String FIELDS_CATEGORY = "?fields=id,count,description,name";
    String FIELDS_TAG = "?fields=id,name";

    /*POST*/
    @GET("wp-json/wp/v2/posts" + FIELDS_POST)
    Observable<List<PostData>> getPosts(
            @Query("categories") String categoryId
            , @Query("tags") String tags
            , @Query("page") int page
    );

    @GET("wp-json/wp/v2/posts/{postId}" + FIELDS_POST)
    Observable<PostData> getPost(@Path("postId") String postId);

    /*Categories*/
    @GET("wp-json/wp/v2/categories" + FIELDS_CATEGORY)
    Observable<List<CategoryData>> getCategories();

    @GET("wp-json/wp/v2/categories/{categoryId}" + FIELDS_CATEGORY)
    Observable<CategoryData> getCategory(@Path("categoryId") String categoryId);

    /*Tags*/
    @GET("wp-json/wp/v2/tags" + FIELDS_TAG)
    Observable<List<TagData>> getTags();

    /*Pages*/
    @GET("wp-json/wp/v2/pages" + FIELDS_POST)
    Observable<List<PostData>> getPages();

    /*CUSTOM ENDPOINTS*/
    @GET("wp-json/techdevfan/v1/config")
    Observable<ConfigData> getConfigData();

    @GET("wp-json/techdevfan/v1/favoritePost")
    Observable<List<PostData>> getFavoritePost(@Query("postIds") String postIds);


}

