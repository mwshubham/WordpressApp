package com.techdevfan.wordpressapp.helper;

/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;

import com.techdevfan.wordpressapp.R;

import java.util.Locale;

/**
 * Created by shubham on 24/7/17.
 */

public class TimeHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "TimeHelper";

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static String getTimeAgo(Context context, long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return context.getString(R.string.just_now);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return context.getString(R.string.a_min_ago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return String.format(Locale.getDefault(), "%d %s", diff / MINUTE_MILLIS, context.getString(R.string.minutes_ago));
        } else if (diff < 90 * MINUTE_MILLIS) {
            return context.getString(R.string.an_hour_ago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return String.format(Locale.getDefault(), "%d %s", diff / HOUR_MILLIS, context.getString(R.string.hours_ago));
        } else if (diff < 48 * HOUR_MILLIS) {
            return context.getString(R.string.yesterday);
        } else {
            return String.format(Locale.getDefault(), "%d %s", diff / DAY_MILLIS, context.getString(R.string.days_ago));
        }
    }
}
