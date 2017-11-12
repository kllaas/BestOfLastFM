package com.example.alexey.bestoflastfm.ui.details_artist;


import android.util.Log;

import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.ui.base.BasePresenterImpl;
import com.example.alexey.bestoflastfm.ui.list_artists.OnClick;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class DetailsArtistPresenter<V extends DetailsArtistMVPContract.View>
        extends BasePresenterImpl<V> implements DetailsArtistMVPContract.Presenter<V>, OnClick<Album> {

    private Artist artist;

    @Inject
    DetailsArtistPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    protected void onViewPrepared() {
        if (artist == null) return;

        if (artist.getUrlToImage() != null) {
            getView().setImage(artist.getUrlToImage());
        }

        if (artist.getName() != null)
            getView().setName(artist.getName());

        getView().setListeners(artist.getListeners());

        getView().toggleProgressBar(true);

        getCompositeDisposable().add(getRepository()
                .fetchAlbums(artist.getName())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::onLoaded,
                        throwable -> onLoadingFailed(throwable.getLocalizedMessage()))
        );
    }

    private void onLoadingFailed(String localizedMessage) {
        Log.d("onLoadingFailed", localizedMessage);
        getView().toggleProgressBar(false);
    }

    private void onLoaded(List<Album> albums) {
        if (albums != null && albums.size() != 0) {
            getView().updateDataSet(albums);
        }

        getView().toggleProgressBar(false);
    }

    @Override
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public OnClick<Album> getOnItemClick() {
        return this;
    }

    @Override
    public void onClick(Album album) {
        getNavigationManager().openDetailsAlbumFragment(album, getView().getChildFragmentManager());
    }
}
