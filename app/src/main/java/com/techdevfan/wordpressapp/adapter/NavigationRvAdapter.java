package com.techdevfan.wordpressapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techdevfan.wordpressapp.R;
import com.techdevfan.wordpressapp.database.AppDatabase;
import com.techdevfan.wordpressapp.databinding.ItemNavigationRvBinding;
import com.techdevfan.wordpressapp.handler.ItemNavigationRvHandler;
import com.techdevfan.wordpressapp.helper.EnumHelper;
import com.techdevfan.wordpressapp.model.CategoryData;
import com.techdevfan.wordpressapp.model.post.PostData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.techdevfan.wordpressapp.helper.EnumHelper.NavItemType.TYPE_CATEGORY;
import static com.techdevfan.wordpressapp.helper.EnumHelper.NavItemType.TYPE_PAGE;

/**
 * Created by shubham on 22/7/17.
 */

public class NavigationRvAdapter extends RecyclerView.Adapter<NavigationRvAdapter.ViewHolder> {
    @SuppressWarnings("unused")
    private static final String TAG = "PostListFragmentRvAdapt";

    private final Context mContext;
    private EnumHelper.NavItemType mNavItemType;
    private List<CategoryData> mCategoryDatas = new ArrayList<>();
    private List<PostData> mPostDatas = new ArrayList<>();

    public NavigationRvAdapter(Context context, EnumHelper.NavItemType navItemType, @Nullable List<PostData> pageDatas) {
        mContext = context;
        mNavItemType = navItemType;
        switch (navItemType) {
            case TYPE_CATEGORY:
                CategoryData latestCategoryData = new CategoryData();
                latestCategoryData.setName(mContext.getString(R.string.latest));
                mCategoryDatas.add(latestCategoryData);
                mCategoryDatas.addAll(AppDatabase.getAppDatabase(mContext).getCategoryDao().getAll());
                break;

            case TYPE_PAGE:
                mPostDatas = pageDatas;
                break;
        }
    }

    @Override
    public NavigationRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_navigation_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigationRvAdapter.ViewHolder holder, int position) {
        switch (mNavItemType) {
            case TYPE_CATEGORY:
                holder.binding.setName(mCategoryDatas.get(position).getName());
                holder.binding.setPosition(position);
                holder.binding.setCount(mCategoryDatas.get(position).getCount());
                holder.binding.setHandler(new ItemNavigationRvHandler(mContext, TYPE_CATEGORY, null));
                break;

            case TYPE_PAGE:
                holder.binding.setName(mPostDatas.get(position).getTitle().getRendered());
                holder.binding.setPosition(position);
                holder.binding.setHandler(new ItemNavigationRvHandler(mContext, TYPE_PAGE, mPostDatas.get(position)));
                break;
        }

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        switch (mNavItemType) {
            case TYPE_CATEGORY:
                return mCategoryDatas.size();

            case TYPE_PAGE:
                return mPostDatas.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemNavigationRvBinding binding;

        ViewHolder(ItemNavigationRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
