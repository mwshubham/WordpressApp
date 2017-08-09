package com.techdevfan.wordpressapp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.databinding.FragmentEmptyBinding;

import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_DRAWABLE_ID;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_SUBTITLE;
import static com.techdevfan.wordpressapp.constant.BundleConstant.BUNDLE_KEY_TITLE;

/**
 * Created by shubham on 23/7/17.
 */

public class EmptyFragment extends BaseFragment {
    @SuppressWarnings("unused")
    private static final String TAG = "EmptyFragment";
    private FragmentEmptyBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_empty, container, false);
        return mBinding.getRoot();
    }


    /*todo check https://materialdesignicons.com/ for "empty" icons */
    public static EmptyFragment newInstance(int drawableId, String title, String subtitle) {
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_DRAWABLE_ID, drawableId);
        args.putString(BUNDLE_KEY_TITLE, title);
        args.putString(BUNDLE_KEY_SUBTITLE, subtitle);
        EmptyFragment fragment = new EmptyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.setImage(getArguments().getInt(BUNDLE_KEY_DRAWABLE_ID));
        mBinding.setTitle(getArguments().getString(BUNDLE_KEY_TITLE));
        mBinding.setSubtitle(getArguments().getString(BUNDLE_KEY_SUBTITLE));
        mBinding.executePendingBindings();
    }
}
