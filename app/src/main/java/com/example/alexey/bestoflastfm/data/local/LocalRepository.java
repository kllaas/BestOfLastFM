package com.example.alexey.bestoflastfm.data.local;


import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalRepository implements LocalSource {

    private AppDatabase database;

    @Inject
    public LocalRepository(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void saveArtists(List<Artist> artist) {
        database.artistsDAO().insertAll(artist);
    }

    @Override
    public Observable<List<Artist>> getArtists(String country) {
        return Observable.fromCallable(() -> database.artistsDAO().getByCountry(country));
    }

    @Override
    public void saveAlbums(List<Album> albums) {
        database.albumsDao().insertAll(albums);
    }

    @Override
    public Observable<List<Album>> getAlbums(String artistName) {
        return Observable.fromCallable(() -> database.albumsDao().getByArtistName(artistName));
    }
}
