package com.techdevfan.wordpressapp.constant;

/**
 * Created by shubham on 21/7/17.
 */

public interface ApplicationConstant {
    int DEFAULT_PAGE_CHANGE_DELAY_TIME_IN_MILLIS = 2000;
    //    String BASE_URL = "http://www.techdevfan.com/";
    String BASE_URL = "http://www.chintanrathod.com/";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    String DEFAULT_DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    int AVERATE_READ_TIME_WPM = 200;
    int MAX_READ_TIME = 18;
    String ADMOB_APP_ID = "ca-app-pub-7865546675450293~8313945198";


    /*NETWORK*/
    int DEFAULT_READ_TIMEOUT_SEC = 45;
    int DEFAULT_CONNECT_TIMEOUT_SEC = 45;
    int MAX_RETRY_COUNT = 3;


    /*BOADCAST EVENT NAME ACTION NAME*/
    String WP_POST_UPDATED = "WP_POST_UPDATED";
}
