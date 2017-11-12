package com.example.alexey.bestoflastfm.utils;

/**
 * Created by alexey
 */

public interface Constants {

    interface LastFM {

        int ALBUMS_COUNT = 10;
        String ALBUMS_METHOD_TYPE = "artist.gettopalbums";
        String ARTIST_METHOD_TYPE = "geo.gettopartists";

    }

    public interface DB {

        String ARTISTS_DB_NAME = "artists";
        String ALBUMS_DB_NAME = "albums";

    }
}
