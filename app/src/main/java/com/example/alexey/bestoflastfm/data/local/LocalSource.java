package com.example.alexey.bestoflastfm.data.local;

import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;

import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {

    void saveArtists(List<Artist> stations);

    Observable<List<Artist>> getArtists(String country);

    void saveAlbums(List<Album> albums);

    Observable<List<Album>> getAlbums(String artistName);
}
