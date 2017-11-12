package com.example.alexey.bestoflastfm.data.remote;


import com.example.alexey.bestoflastfm.data.entity.remote.ArtistRemote;

import java.util.List;

import io.reactivex.Observable;

public interface RemoteSource {

    Observable<List<ArtistRemote>> fetchArtists(String country);

}
