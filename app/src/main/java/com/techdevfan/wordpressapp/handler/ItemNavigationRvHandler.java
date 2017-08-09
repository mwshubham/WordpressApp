package com.techdevfan.wordpressapp.handler;

import android.content.Context;
import android.content.Intent;

import com.techdevfan.wordpressapp.activity.HomeActivity;
import com.techdevfan.wordpressapp.activity.PostDetailActivity;
import com.techdevfan.wordpressapp.activity.PostListActivity;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.model.post.PostData;

import io.reactivex.annotations.Nullable;

import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_POST_ID;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_SHOW_FAVORITE_POST;

/**
 * Created by shubham on 25/7/17.
 */

public class ItemNavigationRvHandler {
    @SuppressWarnings("unused")
    private static final String TAG = "ItemNavigationRvHandler";
    private Context mContext;
    private EnumHelper.NavItemType mNavItemType;
    private PostData mPageData;

    public ItemNavigationRvHandler(Context context, EnumHelper.NavItemType navItemType, @Nullable PostData pageData) {
        mContext = context;
        mNavItemType = navItemType;
        mPageData = pageData;
    }

    public void performAction(int position) {
        ((HomeActivity) mContext).mBinding.drawerLayout.closeDrawers();
        switch (mNavItemType) {
            case TYPE_FAVORITE:
                Intent intent = new Intent(mContext, PostListActivity.class);
                intent.putExtra(BUNDLE_KEY_SHOW_FAVORITE_POST, true);
                mContext.startActivity(intent);
                break;

            case TYPE_CATEGORY:
                ((HomeActivity) mContext).mBinding.viewPager.setCurrentItem(position);
                break;

            case TYPE_PAGE:
                Intent pageDetailIntent = new Intent(mContext, PostDetailActivity.class);
                pageDetailIntent.putExtra(BUNDLE_KEY_POST_ID, mPageData.getId());
                mContext.startActivity(pageDetailIntent);
                break;
        }
    }
}
