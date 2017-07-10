package com.software.jgodort.graffpaper.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CategoriesItem{

	@SerializedName("photo_count")
	private int photoCount;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setPhotoCount(int photoCount){
		this.photoCount = photoCount;
	}

	public int getPhotoCount(){
		return photoCount;
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

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"photo_count = '" + photoCount + '\'' + 
			",links = '" + links + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}