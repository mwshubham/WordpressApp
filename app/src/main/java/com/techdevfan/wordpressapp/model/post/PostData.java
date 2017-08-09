package com.techdevfan.wordpressapp.model.post;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.util.Log;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.constant.ApplicationConstant;
import com.techdevfan.wordpressapp.helper.Helper;
import com.techdevfan.wordpressapp.helper.TimeHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.techdevfan.wordpressapp.constant.ApplicationConstant.MAX_READ_TIME;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_AUTHOR;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_CATEGORIES;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_CONTENT;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_DATE;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_EXCEPT;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_FEATURED_MEDIA;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_ID;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_LINK;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_TAGS;
import static com.techdevfan.wordpressapp.database.AppDatabase.COLUMN_NAME_TITLE;
import static com.techdevfan.wordpressapp.database.AppDatabase.POST_TABLE_NAME;

/**
 * SAME CLASS IS USED FOR PAGE AS WELL
 * Created by shubham on 22/7/17.
 */
@Entity(tableName = POST_TABLE_NAME)
public class PostData {
    @SuppressWarnings("unused")
    private static final String TAG = "PostData";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_ID)
    @SerializedName(COLUMN_NAME_ID)
    @Expose
    public String id;

    @ColumnInfo(name = COLUMN_NAME_DATE)
    @SerializedName(COLUMN_NAME_DATE)
    @Expose
    public String date;

    @Embedded(prefix = COLUMN_NAME_TITLE)
    @SerializedName(COLUMN_NAME_TITLE)
    @Expose
    public TitleData title;

    @Embedded(prefix = COLUMN_NAME_CONTENT)
    @SerializedName(COLUMN_NAME_CONTENT)
    @Expose
    public ContentData content;

    @Embedded(prefix = COLUMN_NAME_EXCEPT)
    @SerializedName(COLUMN_NAME_EXCEPT)
    @Expose
    public ExcerptData excerpt;

    @ColumnInfo(name = COLUMN_NAME_AUTHOR)
    @SerializedName(COLUMN_NAME_AUTHOR)
    @Expose
    public String author;

    @ColumnInfo(name = COLUMN_NAME_LINK)
    @SerializedName(COLUMN_NAME_LINK)
    @Expose
    public String link;

    @ColumnInfo(name = COLUMN_NAME_FEATURED_MEDIA)
    @SerializedName(COLUMN_NAME_FEATURED_MEDIA)
    @Expose
    public String featuredMedia;

    @ColumnInfo(name = COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD)
    @SerializedName(COLUMN_NAME_FEATURED_IMAGE_THUMBNAIL_STANDARD)
    @Expose
    public String featuredImageThumbnailStandard;

    @ColumnInfo(name = COLUMN_NAME_CATEGORIES)
    @SerializedName(COLUMN_NAME_CATEGORIES)
    @Expose
    public List<String> categories = null;

    @ColumnInfo(name = COLUMN_NAME_TAGS)
    @SerializedName(COLUMN_NAME_TAGS)
    @Expose
    public List<String> tags = null;

    public PostData() {
    }

    public String getId() {
        if (id == null) {
            return "";
        }
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public String getDate() {
        if (date == null) {
            return "";
        }

        return date;
    }

    public String getFormatedDate(Context context) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstant.DEFAULT_DATE_FORMAT, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return TimeHelper.getTimeAgo(context, sdf.parse(getDate()).getTime());
        } catch (ParseException e) {
            Log.i(TAG, "getFormatedDate: " + e.getMessage());
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstant.DEFAULT_DATE_FORMAT_2, Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                return TimeHelper.getTimeAgo(context, sdf.parse(getDate()).getTime());
            } catch (ParseException e1) {
                Log.i(TAG, "getFormatedDate: " + e1.getMessage());
            }
        }
        return "";
    }

    public TitleData getTitle() {
        if (title == null) {
            return new TitleData();
        }

        return title;
    }

    public ContentData getContent() {
        if (content == null) {
            return new ContentData();
        }

        return content;
    }

    public ExcerptData getExcerpt() {
        if (excerpt == null) {
            return new ExcerptData();
        }

        return excerpt;
    }

    public String getAuthor() {
        if (author == null) {
            return "";
        }

        return author;
    }

    public String getLink() {
        if (link == null) {
            return "";
        }
        return link;
    }

    public String getFeaturedMedia() {
        if (featuredMedia == null) {
            return "";
        }
        return featuredMedia;
    }

    public String getFeaturedImageThumbnailStandard() {
        if (featuredImageThumbnailStandard == null) {
            return "";
        }
        return featuredImageThumbnailStandard;
    }

    public List<String> getCategories() {
        if (categories == null) {
            return new ArrayList<>();
        }

        return categories;
    }

    public List<String> getTags() {
        if (tags == null) {
            return new ArrayList<>();
        }

        return tags;
    }

    public String getMinTimeToRead(Context context) {
        return (String.format(Locale.getDefault(), "%d %s", Math.min(Helper.fromHtml(getContent().getRendered()).length() / ApplicationConstant.AVERATE_READ_TIME_WPM, MAX_READ_TIME), context.getString(R.string.min_read)));
    }

}
