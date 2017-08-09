package com.techdevfan.wordpressapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by shubham on 26/7/17.
 */

public class SharedPreferenceHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "SharedPreferenceHelper";
    private final static String CONFIG_PREF_FILE = "CONFIG_PREF";

    public final static String KEY_IS_AD_ENABLED = "IS_AD_ENABLED";
    public final static String KEY_IS_HIDE_CATEGORY_WITH_NO_POST = "IS_HIDE_CATEGORY_WITH_NO_POST";
    public final static String KEY_IS_TAGS_ENABLED = "IS_TAGS_ENABLED";
    public final static String KEY_IS_SHOW_MIN_READ_TIME = "IS_SHOW_MIN_READ_TIME";
    public final static String KEY_IS_SHOW_CUSTOM_PAGES = "IS_SHOW_CUSTOM_PAGES";

    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(CONFIG_PREF_FILE, MODE_PRIVATE);
        return settings.getBoolean(key, defValue);
    }
}