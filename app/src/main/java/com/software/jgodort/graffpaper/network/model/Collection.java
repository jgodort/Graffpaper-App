package com.software.jgodort.graffpaper.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Collection{

	@SerializedName("private")
	private boolean jsonMemberPrivate;

	@SerializedName("cover_photo")
	private CoverPhoto coverPhoto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_photos")
	private int totalPhotos;

	@SerializedName("curated")
	private boolean curated;

	@SerializedName("share_key")
	private String shareKey;

	@SerializedName("description")
	private String description;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("published_at")
	private String publishedAt;

	@SerializedName("user")
	private User user;

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setCoverPhoto(CoverPhoto coverPhoto){
		this.coverPhoto = coverPhoto;
	}

	public CoverPhoto getCoverPhoto(){
		return coverPhoto;
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

	public void setCurated(boolean curated){
		this.curated = curated;
	}

	public boolean isCurated(){
		return curated;
	}

	public void setShareKey(String shareKey){
		this.shareKey = shareKey;
	}

	public String getShareKey(){
		return shareKey;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Collection{" + 
			"private = '" + jsonMemberPrivate + '\'' + 
			",cover_photo = '" + coverPhoto + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_photos = '" + totalPhotos + '\'' + 
			",curated = '" + curated + '\'' + 
			",share_key = '" + shareKey + '\'' + 
			",description = '" + description + '\'' + 
			",links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",published_at = '" + publishedAt + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}