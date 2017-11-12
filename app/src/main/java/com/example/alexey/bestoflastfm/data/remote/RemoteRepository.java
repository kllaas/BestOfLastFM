package com.example.alexey.bestoflastfm.data.remote;

import com.example.alexey.bestoflastfm.BuildConfig;
import com.example.alexey.bestoflastfm.data.entity.remote.AlbumRemote;
import com.example.alexey.bestoflastfm.data.entity.remote.AlbumResponse;
import com.example.alexey.bestoflastfm.data.entity.remote.ArtistRemote;
import com.example.alexey.bestoflastfm.data.entity.remote.ArtistsResponse;
import com.example.alexey.bestoflastfm.data.remote.service.LastFMService;
import com.example.alexey.bestoflastfm.data.remote.service.ServiceGenerator;
import com.example.alexey.bestoflastfm.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;

    private LastFMService lastFMService;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;

        this.lastFMService = serviceGenerator.createService(LastFMService.class, BuildConfig.BASE_URL);
    }

    @Override
    public Observable<List<ArtistRemote>> fetchArtists(String country) {
        return lastFMService
                .getPopularStations(Constants.LastFM.ARTIST_METHOD_TYPE, country)
                .map(ArtistsResponse::getArtists);
    }

    public Observable<List<AlbumRemote>> fetchAlbums(String artistName) {
        return lastFMService
                .getAlbums(Constants.LastFM.ALBUMS_METHOD_TYPE, artistName, Constants.LastFM.DEFAULT_ALBUMS_COUNT)
                .map(AlbumResponse::getAlbums);
    }
}
