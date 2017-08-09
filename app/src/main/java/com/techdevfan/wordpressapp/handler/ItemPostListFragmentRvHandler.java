package com.techdevfan.wordpressapp.handler;

import android.content.Context;
import android.content.Intent;

import com.techdevfan.wordpressapp.activity.PostDetailActivity;
import com.techdevfan.wordpressapp.model.post.PostData;

import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_POST_DATA;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_POST_ID;

/**
 * Created by shubham on 24/7/17.
 */

public class ItemPostListFragmentRvHandler {
    @SuppressWarnings("unused")
    private static final String TAG = "ItemPostListFragmentRvH";
    private Context mContext;
    private PostData mPostData;

    public ItemPostListFragmentRvHandler(Context context, PostData postData) {
        mContext = context;
        mPostData = postData;
    }

    public void viewPostDetails() {
        Intent intent = new Intent(mContext, PostDetailActivity.class);
        intent.putExtra(BUNDLE_KEY_POST_ID, mPostData.getId());
        mContext.startActivity(intent);
    }
}
