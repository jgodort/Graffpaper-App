package com.software.jgodort.graffpaper.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProfileImage implements Parcelable {

	@SerializedName("small")
	private String small;

	@SerializedName("large")
	private String large;

	@SerializedName("medium")
	private String medium;

	public void setSmall(String small){
		this.small = small;
	}

	public String getSmall(){
		return small;
	}

	public void setLarge(String large){
		this.large = large;
	}

	public String getLarge(){
		return large;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}

	@Override
 	public String toString(){
		return 
			"ProfileImage{" + 
			"small = '" + small + '\'' + 
			",large = '" + large + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.small);
		dest.writeString(this.large);
		dest.writeString(this.medium);
	}

	public ProfileImage() {
	}

	protected ProfileImage(Parcel in) {
		this.small = in.readString();
		this.large = in.readString();
		this.medium = in.readString();
	}

	public static final Parcelable.Creator<ProfileImage> CREATOR = new Parcelable.Creator<ProfileImage>() {
		@Override
		public ProfileImage createFromParcel(Parcel source) {
			return new ProfileImage(source);
		}

		@Override
		public ProfileImage[] newArray(int size) {
			return new ProfileImage[size];
		}
	};
}