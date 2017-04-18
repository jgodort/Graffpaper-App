
package com.software.jgodort.graffpaper.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("downloads")
    @Expose
    private Downloads downloads;
    @SerializedName("views")
    @Expose
    private Views views;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Downloads getDownloads() {
        return downloads;
    }

    public void setDownloads(Downloads downloads) {
        this.downloads = downloads;
    }

    public Views getViews() {
        return views;
    }

    public void setViews(Views views) {
        this.views = views;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

}
