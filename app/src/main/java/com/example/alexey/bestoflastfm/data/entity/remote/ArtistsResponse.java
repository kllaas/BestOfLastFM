package com.example.alexey.bestoflastfm.data.entity.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexey
 */

public class ArtistsResponse {

    @SerializedName("topartists")
    private TopArtists topArtists;

    public List<ArtistRemote> getArtists() {
        return topArtists.getArtists();
    }

    public class TopArtists {

        @SerializedName("artist")
        List<ArtistRemote> artists;

        public List<ArtistRemote> getArtists() {
            return artists;
        }
    }
}
