package com.software.jgodort.graffpaper.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.network.model.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 14/04/2017.
 */

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {


    private List<Image> mImages;
    private Context mContext;

    private View mEmptyView;

    public WallpaperAdapter(Context context, View emptyView) {
        this.mContext = context;
        this.mEmptyView = emptyView;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Image image = mImages.get(position);
        // holder.mWallpaperThumb.setImageResource(R.drawable.sample1);
        Glide.with(mContext).
                load(image.getUrls().getThumb()).
                //override(150, 200).
                centerCrop().
                into(holder.mWallpaperThumb);

    }

    @Override
    public int getItemCount() {
        mEmptyView.setVisibility(mImages != null && !mImages.isEmpty() ? View.GONE : View.VISIBLE);
        return mImages != null ? mImages.size() : 0;
    }

    public List<Image> getmImages() {
        return mImages;
    }

    public void setmImages(List<Image> mImages) {
        this.mImages = mImages;
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_thumb)
        ImageView mWallpaperThumb;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

}
