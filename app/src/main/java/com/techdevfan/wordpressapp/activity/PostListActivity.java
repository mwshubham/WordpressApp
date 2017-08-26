package com.techdevfan.wordpressapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.databinding.ActivityPostListBinding;
import com.techdevfan.wordpressapp.fragment.PostListFragment;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.helper.Helper;

import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_SHOW_FAVORITE_POST;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_TAG_ID;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_TAG_NAME;

/**
 * Created by shubham on 27/7/17.
 */

public class PostListActivity extends BaseActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "PostListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPostListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list);
        setSupportActionBar(binding.toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        onNewIntent(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (getIntent().hasExtra(BUNDLE_KEY_SHOW_FAVORITE_POST) && getIntent().getExtras().getBoolean(BUNDLE_KEY_SHOW_FAVORITE_POST)) {
            //noinspection ConstantConditions
            getSupportActionBar().setTitle(R.string.favorite);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, PostListFragment.newInstance(true)).commit();
        } else {
            //noinspection ConstantConditions
            getSupportActionBar().setTitle(Helper.initCap(getIntent().getExtras().getString(BUNDLE_KEY_TAG_NAME)));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, PostListFragment.newInstance(getIntent().getExtras().getString(BUNDLE_KEY_TAG_ID), EnumHelper.IdType.TAG_ID)).commit();
        }

    }

}
