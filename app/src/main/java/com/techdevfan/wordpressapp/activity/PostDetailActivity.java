package com.techdevfan.wordpressapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.databinding.ActivityPostDetailBinding;
import com.techdevfan.wordpressapp.model.post.PostData;

/**
 * Created by shubham on 24/7/17.
 */

public class PostDetailActivity extends BaseActivity {
    @SuppressWarnings("unused")
    private static final String TAG = "PostDetailActivity";
    private ActivityPostDetailBinding mBinding;
    private PostData mPostData;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail);
        setSupportActionBar(mBinding.toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        mPostData = AppDatabase.getAppDatabase(this).getPostDao().getPost((String) getIntent().getExtras().get(BUNDLE_KEY_POST_ID));
//
//        if (mPostData != null) {
//            mBinding.setData(mPostData);
//            mBinding.webView.setDrawingCacheEnabled(true);
//            mBinding.webView.getSettings().setBuiltInZoomControls(true);
//            mBinding.webView.getSettings().setDisplayZoomControls(false);
//            mBinding.webView.getSettings().setUseWideViewPort(true);
//            mBinding.webView.getSettings().setLoadWithOverviewMode(true);
//
//            mBinding.webView.setWebChromeClient(new WebChromeClient() {
//            });
//
//            mBinding.webView.getSettings().setJavaScriptEnabled(true);
////            mBinding.webView.getSettings().setJavaScriptEnabled(true);
//
//            mBinding.webView.setWebViewClient(new WebViewClient() {
//                @Override
//                public void onPageStarted(WebView webView, String url, Bitmap favicon) {
//                    super.onPageStarted(webView, url, favicon);
//                    webView.setVisibility(View.GONE);
//                    mBinding.animationProgressBar.playAnimation();
//                    mBinding.animationProgressBar.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onPageFinished(WebView webView, String url) {
//                    super.onPageFinished(webView, url);
//                    webView.setVisibility(View.VISIBLE);
//                    mBinding.animationProgressBar.setVisibility(View.GONE);
//                    mBinding.animationProgressBar.cancelAnimation();
//
//                }
//            });
//            mBinding.webView.loadDataWithBaseURL(null, getHtmlData(), "text/html", "utf-8", null);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                try {
                    if (mPostData != null) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                        i.putExtra(Intent.EXTRA_TEXT, mPostData.getLink());
                        startActivity(Intent.createChooser(i, null));
                        return true;
                    }
                } catch (Exception e) {
                    Toast.makeText(this, R.string.error_cannot_share_post, Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getHtmlData() {
        return "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
                + "<HTML xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en' class='gr__store_webkul_com'>"
                + "<HEAD>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<link id='hu-user-gfont' href='//fonts.googleapis.com/css?family=Source+Sans+Pro:400,300italic,300,400italic,600&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>"
                + "<link rel='stylesheet' id='crayon-css'  href='http://techdevfan.com/wp-content/plugins/crayon-syntax-highlighter/css/min/crayon.min.css?ver=_2.7.2_beta' type='text/css' media='all' />"
                + "<link rel='stylesheet' id='crayon-theme-classic-css' href='http://techdevfan.com/wp-content/plugins/crayon-syntax-highlighter/themes/classic/classic.css?ver=_2.7.2_beta' type='text/css' media='all'>"
                + "<link rel='stylesheet' id='crayon-font-monaco-css' href='http://techdevfan.com/wp-content/plugins/crayon-syntax-highlighter/fonts/monaco.css?ver=_2.7.2_beta' type='text/css' media='all'>"
                + "<link rel='stylesheet' id='contact-form-7-css' href='http://techdevfan.com/wp-content/plugins/contact-form-7/includes/css/styles.css?ver=4.8' type='text/css' media='all'>"
                + "<link rel='stylesheet' id='hueman-main-style-css'  href='http://techdevfan.com/wp-content/themes/hueman/assets/front/css/main.min.css?ver=3.3.18' type='text/css' media='all' />"
                + "<link rel='stylesheet' id='theme-stylesheet-css'  href='http://techdevfan.com/wp-content/themes/hueman/style.css?ver=3.3.18' type='text/css' media='all' />"
                + "<link rel='stylesheet' id='hueman-font-awesome-css'  href='http://techdevfan.com/wp-content/themes/hueman/assets/front/css/font-awesome.min.css?ver=3.3.18' type='text/css' media='all' />"

                + "</HEAD>"
                + "<BODY style='background-color: #FFFFFF;text-align:left;'>"
                + mPostData.getContent().getRendered()
                + "</BODY>"
                + "</HTML>";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseWebView();
    }

    private void releaseWebView() {
        if (mBinding.webView != null) {
            mBinding.webView.setTag(null);
            mBinding.webView.clearHistory();
            mBinding.webView.removeAllViews();
            mBinding.webView.loadUrl("about:blank"); // to clear view
            mBinding.webView.destroy();
        }
    }
}
