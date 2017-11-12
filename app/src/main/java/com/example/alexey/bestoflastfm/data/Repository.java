package com.example.alexey.bestoflastfm.data;

import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.data.entity.mappers.AlbumRemoteToPOJO;
import com.example.alexey.bestoflastfm.data.entity.mappers.ArtistRemoteToPOJO;
import com.example.alexey.bestoflastfm.data.local.LocalRepository;
import com.example.alexey.bestoflastfm.data.remote.RemoteRepository;
import com.example.alexey.bestoflastfm.utils.network.NetworkUtils;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private NetworkUtils networkUtils;

    private SchedulerProvider schedulerProvider;

    @Inject
    public Repository(LocalRepository localRepository,
                      RemoteRepository remoteRepository,
                      NetworkUtils networkUtils,
                      SchedulerProvider schedulerProvider) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.networkUtils = networkUtils;
        this.schedulerProvider = schedulerProvider;
    }

    public Observable<List<Artist>> fetchArtists(String country) {

        return networkUtils.isNetworkAvailable() ? getRemoteArtistsObservable(country) :
                getLocalArtistsObservable(country);
    }

    public Observable<List<Album>> fetchAlbums(String artistName) {

        return networkUtils.isNetworkAvailable() ? getRemoteAlbumsObservable(artistName) :
                getLocalAlbumsObservable(artistName);

    }


    private Observable<List<Artist>> getRemoteArtistsObservable(String country) {
        return remoteRepository.fetchArtists(country)
                .map(artistRemotes -> ArtistRemoteToPOJO.transformList(artistRemotes, country))
                .doOnNext(artists -> {
                    Observable.create(subscriber -> {

                        localRepository.saveArtists(artists);
                        subscriber.onComplete();

                    }).subscribeOn(schedulerProvider.computation()).subscribe();
                })
                .onErrorResumeNext(Observable.empty())
                .subscribeOn(schedulerProvider.io());
    }

    private Observable<List<Artist>> getLocalArtistsObservable(String country) {
        return localRepository.getArtists(country)
                .filter(items -> items.size() > 0)
                .subscribeOn(schedulerProvider.computation());
    }


    private Observable<List<Album>> getRemoteAlbumsObservable(String artistName) {
        return remoteRepository.fetchAlbums(artistName)
                .map(albumRemotes -> AlbumRemoteToPOJO.transformList(albumRemotes, artistName))
                .doOnNext(albums -> {
                    Observable.create(subscriber -> {

                        localRepository.saveAlbums(albums);
                        subscriber.onComplete();

                    }).subscribeOn(schedulerProvider.computation()).subscribe();
                })
                .onErrorResumeNext(Observable.empty())
                .subscribeOn(schedulerProvider.io());
    }

    private Observable<List<Album>> getLocalAlbumsObservable(String artistName) {
        return localRepository.getAlbums(artistName)
                .filter(items -> items.size() > 0)
                .subscribeOn(schedulerProvider.computation());
    }

}
