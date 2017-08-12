package com.techdevfan.wordpressapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.adapter.PostListFragmentRvAdapter;
import com.techdevfan.wordpressapp.database.AppDatabase;
import com.techdevfan.wordpressapp.databinding.FragmentPostListBinding;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.helper.PostHelper;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.techdevfan.wordpressapp.constant.ApplicationConstant.WP_POST_UPDATED;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_CATEGORY_ID;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_SHOW_FAVORITE_POST;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_TAG_ID;

/**
 * Created by shubham on 22/7/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class PostListFragment extends BaseFragment {
    @SuppressWarnings("unused")
    private static final String TAG = "PostListFragment";
    private FragmentPostListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_list, container, false);
        return mBinding.getRoot();
    }

    public static PostListFragment newInstance(@NonNull String id, EnumHelper.IdType idType) {
        Bundle args = new Bundle();
        if (idType == EnumHelper.IdType.CATEGORY_ID) {
            args.putString(BUNDLE_KEY_CATEGORY_ID, id);
        } else {
            args.putString(BUNDLE_KEY_TAG_ID, id);
        }
        PostListFragment fragment = new PostListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static PostListFragment newInstance(boolean isShowFavorite) {
        Bundle args = new Bundle();
        args.putBoolean(BUNDLE_KEY_SHOW_FAVORITE_POST, isShowFavorite);
        PostListFragment fragment = new PostListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /*todo show only those categories where count of post >=1 or alternatively show empty page.*/
    /*todo create a configuration page [wordpress backend] to choose the application configuration e.g. show empty page or hide categories in the application.*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPosts();

//        CustomObserver<List<PostData>> customObserver = new CustomObserver<List<PostData>>(getContext()) {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                super.onSubscribe(d);
//                mBinding.animationProgressBar.setVisibility(View.VISIBLE);
//                mBinding.animationProgressBar.playAnimation();
//            }
//
//            @Override
//            public void onNext(@NonNull List<PostData> postDatas) {
//                super.onNext(postDatas);
//                AppDatabase.getAppDatabase(getContext()).getPostDao().insertAll(postDatas);
//                mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), postDatas));
//            }
//
//            @Override
//            public void onComplete() {
//                super.onComplete();
//                mBinding.animationProgressBar.setVisibility(View.GONE);
//                mBinding.animationProgressBar.cancelAnimation();
//            }
//        };

//        if (getArguments().getBoolean(BUNDLE_KEY_SHOW_FAVORITE_POST)) {
//            List<FavoriteData> favoriteDatas = AppDatabase.getAppDatabase(getContext()).getFavoriteDao().getAll();
//            List<String> favoritePostIds = new ArrayList<>();
//            for (FavoriteData favoriteData : favoriteDatas) {
//                favoritePostIds.add(favoriteData.getPostId());
//            }
//            if (favoriteDatas.isEmpty()) {
//                Log.i(TAG, "onActivityCreated: NO FAVORITE POST EXIST !!!!!!!!!!!!!!!!");
//                /*todo handle this situation*/
//            } else {
//                ApiConnection.getFavoritePost(getContext(), favoritePostIds).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(customObserver);
//            }
//        } else {
//            Observable<List<PostData>> postListObservable = null;
//            if (getArguments().getString(BUNDLE_KEY_CATEGORY_ID) != null) {
//                mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), AppDatabase.getAppDatabase(getContext()).getPostDao().getAll()));
//                postListObservable = ApiConnection.getPosts(getContext(), getArguments().getString(BUNDLE_KEY_CATEGORY_ID), "", 1);
//            } else if (getArguments().getString(BUNDLE_KEY_TAG_ID) != null) {
//                postListObservable = ApiConnection.getPosts(getContext(), "", getArguments().getString(BUNDLE_KEY_TAG_ID), 1);
//            }
//
//            if (postListObservable != null) {
//                postListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(customObserver);
//            }
//        }
    }

    private void loadPosts() {
        if (getArguments().getString(BUNDLE_KEY_CATEGORY_ID) != null) {
            List<PostData> postDatas = AppDatabase.getAppDatabase(getContext()).getPostDao().getAllPost(PostData.TYPE_POST);
            //noinspection ConstantConditions
            if (getArguments().getString(BUNDLE_KEY_CATEGORY_ID).isEmpty()) {
                mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), postDatas));
            } else {
                mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), PostHelper.getPosts(postDatas, getArguments().getString(BUNDLE_KEY_CATEGORY_ID))));
            }
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadPosts();
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, new IntentFilter(WP_POST_UPDATED));
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

}
