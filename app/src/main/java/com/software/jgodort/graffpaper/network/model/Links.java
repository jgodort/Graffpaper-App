package com.software.jgodort.graffpaper.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Links implements Parcelable {

    @SerializedName("portfolio")
    private String portfolio;

    @SerializedName("self")
    private String self;

    @SerializedName("html")
    private String html;

    @SerializedName("photos")
    private String photos;

    @SerializedName("likes")
    private String likes;

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getSelf() {
        return self;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getPhotos() {
        return photos;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return
                "Links{" +
                        "portfolio = '" + portfolio + '\'' +
                        ",self = '" + self + '\'' +
                        ",html = '" + html + '\'' +
                        ",photos = '" + photos + '\'' +
                        ",likes = '" + likes + '\'' +
                        "}";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.portfolio);
        dest.writeString(this.self);
        dest.writeString(this.html);
        dest.writeString(this.photos);
        dest.writeString(this.likes);
    }

    public Links() {
    }

    protected Links(Parcel in) {
        this.portfolio = in.readString();
        this.self = in.readString();
        this.html = in.readString();
        this.photos = in.readString();
        this.likes = in.readString();
    }

    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
}