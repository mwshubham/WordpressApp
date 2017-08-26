package com.techdevfan.wordpressapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.activity.BaseActivity;
import com.techdevfan.wordpressapp.adapter.PostListFragmentRvAdapter;
import com.techdevfan.wordpressapp.connection.CustomObserver;
import com.techdevfan.wordpressapp.database.helper.PostDbHelper;
import com.techdevfan.wordpressapp.databinding.FragmentPostListBinding;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.helper.PostHelper;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPosts();
    }

    private void loadPosts() {
        PostDbHelper postDbHelper = PostDbHelper.getInstance(getContext());

        /*Load Post by Categories*/
        if (getArguments().getString(BUNDLE_KEY_CATEGORY_ID) != null) {
            io.reactivex.Observable.fromCallable(() -> postDbHelper.getAllPost(PostData.TYPE_POST)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<PostData>>(getContext()) {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    ((BaseActivity) getActivity()).mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(List<PostData> postDatas) {
                    super.onNext(postDatas);
                    //noinspection ConstantConditions
                    if (getArguments().getString(BUNDLE_KEY_CATEGORY_ID).isEmpty()) {
                        mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), postDatas));
                    } else {
                        mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), postDatas));
                        // FIXME: 26/8/17
//                        mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), PostHelper.getPosts(postDatas, getArguments().getString(BUNDLE_KEY_CATEGORY_ID))));
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        } else {
            //noinspection ConstantConditions
            if (getArguments().getString(BUNDLE_KEY_TAG_ID) != null && !getArguments().getString(BUNDLE_KEY_TAG_ID).isEmpty()) {
                io.reactivex.Observable.fromCallable(() -> postDbHelper.getAllPost(PostData.TYPE_POST)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<PostData>>(getContext()) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        ((BaseActivity) getActivity()).mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<PostData> postDatas) {
                        super.onNext(postDatas);
                        mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), PostHelper.getPostsByTag(postDatas, getArguments().getString(BUNDLE_KEY_TAG_ID))));
                    }
                });
            }
        }

        /* Load Favorite posts*/
        // FIXME: 23/8/17
//        loadFavoritePosts();
    }

//    private void loadFavoritePosts() {
//        if (getArguments().getBoolean(BUNDLE_KEY_SHOW_FAVORITE_POST)) {
//            List<FavoriteData> favoriteDatas = AppDatabase.getAppDatabase(getContext()).getFavoriteDao().getAll();
//            List<String> favoritePostIds = new ArrayList<>();
//            List<PostData> favoritePostDatas = new ArrayList<>();
//            for (FavoriteData favoriteData : favoriteDatas) {
//                favoritePostIds.add(favoriteData.getPostId());
//                favoritePostDatas.add(AppDatabase.getAppDatabase(getContext()).getPostDao().getPost(favoriteData.getPostId()));
//            }
//
//            /*loading favorite post from database*/
//            mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), favoritePostDatas));
//
//            if (favoriteDatas.isEmpty()) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, EmptyFragment.newInstance(R.drawable.ic_sentiment_dissatisfied_accent_48px, getString(R.string.favorite_empty_container_title),
//                        getString(R.string.favorite_empty_container_subtitle))).commit();
//            } else {
//                ApiConnection.getFavoritePost(getContext(), favoritePostIds).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<PostData>>(getContext()) {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        ((BaseActivity) getActivity()).mCompositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(@NonNull List<PostData> postDatas) {
//                        super.onNext(postDatas);
//                        AppDatabase.getAppDatabase(getContext()).getPostDao().insertAll(postDatas);
//                        mBinding.recyclerView.setAdapter(new PostListFragmentRvAdapter(getContext(), postDatas));
//                    }
//                });
//            }
//        }
//    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            loadPosts();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, new IntentFilter(WP_POST_UPDATED));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
    }

}
