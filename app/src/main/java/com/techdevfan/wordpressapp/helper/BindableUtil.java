package com.techdevfan.wordpressapp.helper;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techdevfan.wordpressapp.R;

/**
 * Created by shubham on 23/7/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class BindableUtil {

    @SuppressWarnings("unused")
    private static final String TAG = "BindableUtil";


    @BindingAdapter("layout_marginBottom")
    public static void setLayoutMarginBottom(ViewGroup view, float marginBottom) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.bottomMargin = (int) marginBottom;
    }

    @BindingAdapter({"font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

    @BindingAdapter("htmlText")
    public static void setHtmlText(TextView textView, String htmlText) {
        if (htmlText == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            //noinspection deprecation
            textView.setText(Html.fromHtml(htmlText));
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).placeholder(R.drawable.ic_placeholder).into(view);
    }

    @BindingAdapter("srcCompat")
    public static void bindSrcCompat(ImageView imageView, int drawableId) {
        imageView.setImageResource(drawableId);
    }

    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("textAllLowers")
    public static void setTextAllLowers(TextView textView, boolean isLower) {
        if (isLower) {
            textView.setText(textView.getText().toString().toLowerCase());
        }
    }
}
