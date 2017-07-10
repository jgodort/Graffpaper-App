package com.software.jgodort.graffpaper.network.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CoverPhoto{

	@SerializedName("urls")
	private Urls urls;

	@SerializedName("color")
	private String color;

	@SerializedName("width")
	private int width;

	@SerializedName("description")
	private String description;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("liked_by_user")
	private boolean likedByUser;

	@SerializedName("user")
	private User user;

	@SerializedName("height")
	private int height;

	@SerializedName("likes")
	private int likes;

	public void setUrls(Urls urls){
		this.urls = urls;
	}

	public Urls getUrls(){
		return urls;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setLikedByUser(boolean likedByUser){
		this.likedByUser = likedByUser;
	}

	public boolean isLikedByUser(){
		return likedByUser;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	public void setLikes(int likes){
		this.likes = likes;
	}

	public int getLikes(){
		return likes;
	}

	@Override
 	public String toString(){
		return 
			"CoverPhoto{" + 
			"urls = '" + urls + '\'' + 
			",color = '" + color + '\'' + 
			",width = '" + width + '\'' + 
			",description = '" + description + '\'' + 
			",links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",categories = '" + categories + '\'' + 
			",liked_by_user = '" + likedByUser + '\'' + 
			",user = '" + user + '\'' + 
			",height = '" + height + '\'' + 
			",likes = '" + likes + '\'' + 
			"}";
		}
}