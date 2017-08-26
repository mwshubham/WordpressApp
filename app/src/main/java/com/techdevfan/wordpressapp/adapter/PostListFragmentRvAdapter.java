package com.techdevfan.wordpressapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.databinding.ItemPostListFragmentRvBinding;
import com.techdevfan.wordpressapp.handler.ItemPostListFragmentRvHandler;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.List;

import static android.databinding.DataBindingUtil.inflate;

/**
 * Created by shubham on 22/7/17.
 */

public class PostListFragmentRvAdapter extends RecyclerView.Adapter<PostListFragmentRvAdapter.ViewHolder> {
    @SuppressWarnings("unused")
    private static final String TAG = "PostListFragmentRvAdapt";

    private final Context mContext;
    private final List<PostData> mPostDatas;

    public PostListFragmentRvAdapter(Context context, List<PostData> postDatas) {
        mContext = context;
        mPostDatas = postDatas;
    }

    @Override
    public PostListFragmentRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflate(layoutInflater, R.layout.item_post_list_fragment_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(PostListFragmentRvAdapter.ViewHolder holder, int position) {
        holder.binding.setData(mPostDatas.get(position));
        holder.binding.setHandler(new ItemPostListFragmentRvHandler(mContext, mPostDatas.get(position)));
//        FIXME: 24/8/17
//        holder.binding.setIsFavoritePost(AppDatabase.getAppDatabase(mContext).isFavoritePost(mContext, mPostDatas.get(position).getId()));

//        FIXME: 24/8/17
//        if ((SharedPreferenceHelper.getSharedPreferenceBoolean(mContext, SharedPreferenceHelper.KEY_IS_TAGS_ENABLED, false))) {
//            loadTags(holder, position);
//        }
//
//        holder.binding.bookmarkToogleBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                AppDatabase.getAppDatabase(mContext).getFavoriteDao().insert(new FavoriteData(mPostDatas.get(position).getId()));
//            } else {
//                AppDatabase.getAppDatabase(mContext).getFavoriteDao().delete(new FavoriteData(mPostDatas.get(position).getId()));
//            }
//        });

        holder.binding.executePendingBindings();
    }

//    private void loadTags(ViewHolder holder, int position) {
//        List<TagData> tagDatas = AppDatabase.getAppDatabase(mContext).getTagDao().getTagDatas(mPostDatas.get(position).getTags());
//        for (TagData eachTagData : tagDatas) {
//            ItemTagsPostListFragmentRvBinding itemTagsPostListFragmentRvBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_tags_post_list_fragment_rv
//                    , holder.binding.tagsContainer, true);
//            itemTagsPostListFragmentRvBinding.setTagName(eachTagData.getName());
//            itemTagsPostListFragmentRvBinding.getRoot().setOnClickListener(v -> {
//                Intent intent = new Intent(mContext, PostListActivity.class);
//                intent.putExtra(BUNDLE_KEY_TAG_ID, eachTagData.getId());
//                intent.putExtra(BUNDLE_KEY_TAG_NAME, eachTagData.getName());
//                mContext.startActivity(intent);
//            });
//        }
//    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mPostDatas.size());
        return mPostDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostListFragmentRvBinding binding;

        ViewHolder(ItemPostListFragmentRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
