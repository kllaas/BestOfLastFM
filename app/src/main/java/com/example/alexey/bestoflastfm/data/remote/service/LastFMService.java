package com.example.alexey.bestoflastfm.data.remote.service;

import com.example.alexey.bestoflastfm.data.entity.remote.AlbumResponse;
import com.example.alexey.bestoflastfm.data.entity.remote.ArtistsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexey
 */

public interface LastFMService {

    @GET("./")
    Observable<ArtistsResponse> getPopularStations(@Query("method") String method,
                                                   @Query("country") String country);

    @GET("./")
    Observable<AlbumResponse> getAlbums(@Query("method") String method,
                                        @Query("artist") String artist,
                                        @Query("limit") int limit);

}
