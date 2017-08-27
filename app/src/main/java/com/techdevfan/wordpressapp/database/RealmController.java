package com.techdevfan.wordpressapp.database;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;


public class RealmController {

    @SuppressWarnings("unused")
    private static final String TAG = "RealmController";

    private static RealmController instance;
    private final Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void refresh() {
        mRealm.refresh();
    }

//    //clear all objects from Book.class
//    public void clearAll() {
//        mRealm.beginTransaction();
//        mRealm.clear(Book.class);
//        mRealm.commitTransaction();
//    }

//    //find all objects in the Book.class
//    public RealmResults<Book> getBooks() {
//        return mRealm.where(Book.class).findAll();
//    }

//    //query a single item with the given id
//    public Book getBook(String id) {
//        return mRealm.where(Book.class).equalTo("id", id).findFirst();
//    }

//    //check if Book.class is empty
//    public boolean hasBooks() {
//        return !mRealm.allObjects(Book.class).isEmpty();
//    }

//    //query example
//    public RealmResults<Book> queryedBooks() {
//        return mRealm.where(Book.class)
//                .contains("author", "Author 0")
//                .or()
//                .contains("title", "Realm")
//                .findAll();
//
//    }
}