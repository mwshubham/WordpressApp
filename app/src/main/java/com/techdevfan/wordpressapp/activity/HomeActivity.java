package com.techdevfan.wordpressapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.adapter.NavigationRvAdapter;
import com.techdevfan.wordpressapp.connection.ApiConnection;
import com.techdevfan.wordpressapp.connection.CustomObserver;
import com.techdevfan.wordpressapp.database.helper.CategoryDbHelper;
import com.techdevfan.wordpressapp.database.helper.PostDbHelper;
import com.techdevfan.wordpressapp.databinding.ActivityHomeBinding;
import com.techdevfan.wordpressapp.fragment.PostListFragment;
import com.techdevfan.wordpressapp.handler.ItemNavigationRvHandler;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.helper.Helper;
import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

import static com.techdevfan.wordpressapp.constant.ApplicationConstant.WP_POST_UPDATED;

public class HomeActivity extends BaseActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "HomeActivity";
    public ActivityHomeBinding mBinding;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(mBinding.toolbar);

        loadPosts(1);
        setUpViewPager();
        setUpNavigationDrawer();

        // fixme

//        if (SharedPreferenceHelper.getSharedPreferenceBoolean(this, KEY_IS_AD_ENABLED, false)) {
//            setUpAdMob();
//        }

//        if (SharedPreferenceHelper.getSharedPreferenceBoolean(this, KEY_IS_SHOW_CUSTOM_PAGES, false)) {
//            loadCustomPages();
//        }
    }

    private void loadPosts(int page) {
        PostDbHelper postDbHelper = PostDbHelper.getInstance(this);
        ApiConnection.getPosts(this, null, null, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<PostData>>(this) {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull List<PostData> postDataList) {
                super.onNext(postDataList);
                Log.d(TAG, "onNext: " + postDataList.size());
                Observable.fromCallable(() -> {
                    postDbHelper.insertAll(postDataList);
                    return true;
                }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<Boolean>(HomeActivity.this) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Intent intent = new Intent(WP_POST_UPDATED);
                        LocalBroadcastManager.getInstance(HomeActivity.this).sendBroadcast(intent);
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    Response response = httpException.response();
                    /*HANDLING ERROR : {"code":"rest_post_invalid_page_number","message":"The page number requested is larger than the number of pages available.","data":{"status":400}}*/
                    if (response.code() != 400) {
                        super.onError(e);
                    }
                } else {
                    super.onError(e);
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
//                loadPosts(page + 1);
            }
        });
    }

//    private void loadCustomPages() {
//        ApiConnection.getPages(this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<PostData>>(this) {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                mCompositeDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(@NonNull List<PostData> pageDatas) {
//                super.onNext(pageDatas);
//                AppDatabase.getAppDatabase(HomeActivity.this).getPostDao().insertAll(pageDatas);
//                mBinding.pagesRv.setAdapter(new NavigationRvAdapter(HomeActivity.this, EnumHelper.NavItemType.TYPE_PAGE, pageDatas));
//            }
//        });
//    }

//    private void setUpAdMob() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mBinding.bannerAdView.loadAd(adRequest);
//        mBinding.bannerAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                mBinding.viewPager.setPadding(0, 0, 0, mBinding.bannerAdView.getHeight());
//            }
//        });
//    }

    private void setUpViewPager() {
        CategoryDbHelper categoryDbHelper = CategoryDbHelper.getInstance(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PostListFragment.newInstance("", EnumHelper.IdType.CATEGORY_ID), getString(R.string.latest));

        Observable.fromCallable(categoryDbHelper::getAll).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<CategoryData>>(HomeActivity.this) {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(List<CategoryData> categoryDatas) {
                for (int categoryPos = 0; categoryPos < categoryDatas.size(); categoryPos++) {
                    adapter.addFragment(PostListFragment.newInstance(categoryDatas.get(categoryPos).getId(), EnumHelper.IdType.CATEGORY_ID), categoryDatas.get(categoryPos).getName());
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
                mBinding.viewPager.setAdapter(adapter);
                mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
                //        mBinding.viewPager.setOffscreenPageLimit(2);
            }
        });
    }

    private void setUpNavigationDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        CategoryDbHelper categoryDbHelper = CategoryDbHelper.getInstance(this);
        Observable.fromCallable(categoryDbHelper::getAll).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CustomObserver<List<CategoryData>>(HomeActivity.this) {
            @Override
            public void onSubscribe(Disposable d) {
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(List<CategoryData> categoryDataList) {
                super.onNext(categoryDataList);
                List<CategoryData> tempCategoryDataList = new ArrayList<>();
                CategoryData latestCategoryData = new CategoryData(getString(R.string.latest));
                tempCategoryDataList.add(latestCategoryData);
                tempCategoryDataList.addAll(categoryDataList);
                mBinding.categoriesRecyclerView.setAdapter(new NavigationRvAdapter(HomeActivity.this, EnumHelper.NavItemType.TYPE_CATEGORY, tempCategoryDataList, null));
            }
        });

        addFavoriteNavItem();
    }

    private void addFavoriteNavItem() {
        mBinding.itemFavorite.setName(getString(R.string.favorite));
        mBinding.itemFavorite.setPosition(-1);
        mBinding.itemFavorite.setHandler(new ItemNavigationRvHandler(this, EnumHelper.NavItemType.TYPE_FAVORITE, null));
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Helper.fromHtml(mFragmentTitleList.get(position));
        }
    }


    @Override
    public void onPause() {
        if (mBinding.bannerAdView != null) {
            mBinding.bannerAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBinding.bannerAdView != null) {
            mBinding.bannerAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mBinding.bannerAdView != null) {
            mBinding.bannerAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.press_back_again_to_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
