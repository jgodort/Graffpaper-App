package com.software.jgodort.graffpaper.presentation.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.software.jgodort.graffpaper.R;
import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.presentation.ui.fragments.WallpaperImageDetailFragment;

import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javie on 14/04/2017.
 */

public class WallpaperAdapter extends
        RecyclerView.Adapter<WallpaperAdapter.ViewHolder> {


    private List<Image> mImages;
    private Context mContext;
    private OnClickHandler mHandler;

    private View mEmptyView;

    public WallpaperAdapter(Context context, View emptyView, OnClickHandler handler) {
        this.mContext = context;
        this.mEmptyView = emptyView;
        this.mHandler = handler;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Image image = mImages.get(position);

        // holder.mWallpaperThumb.setImageResource(R.drawable.sample1);
        Glide.with(mContext).
                load(image.getUrls().getThumb()).
                centerCrop().placeholder(R.drawable.ic_file_image).
                into(holder.mWallpaperThumb);


        holder.mWallpaperThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.onWallpaperImageSelected(image, holder);
            }
        });

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


    /**
     * View holder pattern for efficiency on the reuse of layout items on scrolling movements
     */
    public final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_thumb)
        public ImageView mWallpaperThumb;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnClickHandler {
        void onWallpaperImageSelected(Image image, WallpaperAdapter.ViewHolder vh);
    }
}
