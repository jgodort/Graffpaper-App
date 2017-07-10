package com.software.jgodort.graffpaper.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class User implements Parcelable {

	@SerializedName("profile_image")
	private ProfileImage profileImage;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_photos")
	private int totalPhotos;

	@SerializedName("name")
	private String name;

	@SerializedName("bio")
	private String bio;

	@SerializedName("location")
	private String location;

	@SerializedName("total_collections")
	private int totalCollections;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("total_likes")
	private int totalLikes;

	@SerializedName("portfolio_url")
	private String portfolioUrl;

	@SerializedName("username")
	private String username;

	public void setProfileImage(ProfileImage profileImage){
		this.profileImage = profileImage;
	}

	public ProfileImage getProfileImage(){
		return profileImage;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setTotalPhotos(int totalPhotos){
		this.totalPhotos = totalPhotos;
	}

	public int getTotalPhotos(){
		return totalPhotos;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setTotalCollections(int totalCollections){
		this.totalCollections = totalCollections;
	}

	public int getTotalCollections(){
		return totalCollections;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTotalLikes(int totalLikes){
		this.totalLikes = totalLikes;
	}

	public int getTotalLikes(){
		return totalLikes;
	}

	public void setPortfolioUrl(String portfolioUrl){
		this.portfolioUrl = portfolioUrl;
	}

	public String getPortfolioUrl(){
		return portfolioUrl;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"profile_image = '" + profileImage + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_photos = '" + totalPhotos + '\'' + 
			",name = '" + name + '\'' + 
			",bio = '" + bio + '\'' + 
			",location = '" + location + '\'' + 
			",total_collections = '" + totalCollections + '\'' + 
			",links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",total_likes = '" + totalLikes + '\'' + 
			",portfolio_url = '" + portfolioUrl + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.profileImage, flags);
		dest.writeString(this.updatedAt);
		dest.writeInt(this.totalPhotos);
		dest.writeString(this.name);
		dest.writeString(this.bio);
		dest.writeString(this.location);
		dest.writeInt(this.totalCollections);
		dest.writeParcelable(this.links, flags);
		dest.writeString(this.id);
		dest.writeInt(this.totalLikes);
		dest.writeString(this.portfolioUrl);
		dest.writeString(this.username);
	}

	public User() {
	}

	protected User(Parcel in) {
		this.profileImage = in.readParcelable(ProfileImage.class.getClassLoader());
		this.updatedAt = in.readString();
		this.totalPhotos = in.readInt();
		this.name = in.readString();
		this.bio = in.readString();
		this.location = in.readString();
		this.totalCollections = in.readInt();
		this.links = in.readParcelable(Links.class.getClassLoader());
		this.id = in.readString();
		this.totalLikes = in.readInt();
		this.portfolioUrl = in.readString();
		this.username = in.readString();
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			return new User(source);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}