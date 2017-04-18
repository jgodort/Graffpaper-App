package com.software.jgodort.graffpaper.network;

import com.software.jgodort.graffpaper.network.model.Image;
import com.software.jgodort.graffpaper.network.model.Statistics;
import com.software.jgodort.graffpaper.network.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by javie on 08/04/2017.
 */


/**
 * This interface represent the endpoints catalog of the Usplash API.
 */
public interface UnsplashAPI {


    /**
     * Get a <>List<{@link Image}></></> of images from the service.
     *
     * @param clientId the client id of the service.
     * @return a List of images.
     */
    @GET("photos")
    Call<List<Image>> getPhotos(@Query("client_id") String clientId);


    /**
     * Get a <>{@link User}</> entity with the information of the user.
     *
     * @param user     user to search.
     * @param clientId the client id of the service.
     * @return a User entity with the related information of the user.
     */
    @GET("users/{user}")
    User getUserInfo(@Path("user") String user, @Header("client_id") String clientId);


    /**
     * Get a list of <>{@link Image}</> of the user.
     *
     * @param user     the user to retrieve the photos.
     * @param clientId the client id of the service.
     * @return a list with the photos of the user.
     */
    @GET("user/{user}/photos")
    List<Image> getUserPhotos(@Path("user") String user,@Query("client_id") String clientId);


    /**
     * Get a <>{@link Image}</> by Id.
     *
     * @param  id the photo Id
     * @param clientId the client id of the service.
     * @return a Image object with the information.
     */
    @GET("photos/{id}")
    Image getPhotoById(@Path("id") String id, @Header("client_id") String clientId);


    /**
     * Get a random photo from the service.
     *
     * @param clientId the client id of the service.
     * @return a <>{@link Image}</> object with a random photo.
     */
    @GET("photos/random")
    Image getRandomPhoto(@Header("client_id") String clientId);

    /**
     * Get the statistics associated to a photo.
     * @param id photo id
     * @param clientId the client id of the service.
     * @return a <>{@link Statistics}</>  object with all the associated data.
     */
    @GET("photos/{id}/statistics")
    Statistics getPhotoStatistics(@Path("id")String id, @Header("client_id") String clientId);


}
