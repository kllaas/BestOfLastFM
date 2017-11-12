package com.example.alexey.bestoflastfm.data.entity.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexey
 */

public class AlbumResponse {

    @SerializedName("topalbums")
    private TopAlbums topalbums;

    public List<AlbumRemote> getAlbums() {
        return topalbums.getAlbums();
    }

    public class TopAlbums {

        @SerializedName("album")
        private List<AlbumRemote> albums;

        List<AlbumRemote> getAlbums() {
            return albums;
        }

    }

}
