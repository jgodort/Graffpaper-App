package com.software.jgodort.graffpaper.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.network.model.CategoriesItem;
import com.software.jgodort.graffpaper.network.model.Collection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jgodort on 9/07/17.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {


    private List<Collection> mCollections;
    private Context mContext;
    private View mEmptyView;


    public CollectionAdapter(Context context, View emptyView) {
        this.mEmptyView = emptyView;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Collection collectionItem = mCollections.get(position);
        Glide.with(mContext).
                load(collectionItem.getCoverPhoto().getUrls().getSmall()).
                centerCrop().placeholder(R.drawable.ic_file_image).
                into(holder.mCollectionPhoto);

        holder.mCollectionTitle.setText(collectionItem.getTitle());
        holder.mCollectionAmountPhotos.setText(mContext.getString(R.string.photo,
                String.valueOf(collectionItem.getTotalPhotos())));

        if (collectionItem.getCoverPhoto().getCategories() != null &&
                !collectionItem.getCoverPhoto().getCategories().isEmpty()) {

            for (CategoriesItem categoryItem : collectionItem.getCoverPhoto().getCategories()) {
                TextView categoryTextview = new TextView(mContext);
                categoryTextview.setText(categoryItem.getTitle());
                categoryTextview.setTextAppearance(mContext, R.style.categoriesTagText);
                holder.mCollectionCategories.addView(categoryTextview);
            }
        } else {
            holder.mCollectionCategories.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        mEmptyView.setVisibility(mCollections != null && !mCollections.isEmpty() ? View.GONE : View.VISIBLE);
        return mCollections != null ? mCollections.size() : 0;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.collection_cover)
        ImageView mCollectionPhoto;

        @BindView(R.id.collection_tittle)
        TextView mCollectionTitle;

        @BindView(R.id.collection_photos)
        TextView mCollectionAmountPhotos;

        @BindView(R.id.collection_categories)
        LinearLayout mCollectionCategories;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public List<Collection> getmCollections() {
        return mCollections;
    }

    public void setmCollections(List<Collection> mCollections) {
        this.mCollections = mCollections;
    }
}
