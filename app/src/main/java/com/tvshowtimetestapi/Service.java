package com.tvshowtimetestapi;

import com.tvshowtimetestapi.classItems.Result;
import com.tvshowtimetestapi.classItems.ResultExplore;
import com.tvshowtimetestapi.classItems.ResultShow;
import com.tvshowtimetestapi.classItems.ResultUser;
import com.tvshowtimetestapi.classItems.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Kévin on 02/04/2016.
 */
public interface Service {

    public static final String ENDPOINT = "https://api.tvshowtime.com/v1";

    static final String token = "";

    @GET("/user" + token)
    void getUser(Callback<ResultUser> callback);
    //void getUser(Callback<User> callback);

    @GET ("/explore" + token)
    void getExplore(@Query("page") int page, @Query("limit") int limit, Callback<ResultExplore> callback);

    @GET ("/show" + token)
    void getShow(@Query("show_id") int show_id, @Query("show_name") String show_name, Callback<ResultShow> callback);

    @POST("/follow" + token)
    void follow(@Body int show_id, Callback<Result> callback);

    @POST("/unfollow" + token)
    void unfollow(@Body int show_id, Callback<Result> callback);


}
