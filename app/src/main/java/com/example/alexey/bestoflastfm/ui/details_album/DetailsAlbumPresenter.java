package com.example.alexey.bestoflastfm.ui.details_album;


import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.ui.base.BasePresenterImpl;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class DetailsAlbumPresenter<V extends DetailsAlbumMVPContract.View>
        extends BasePresenterImpl<V> implements DetailsAlbumMVPContract.Presenter<V> {

    private Album album;

    @Inject
    DetailsAlbumPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    protected void onViewPrepared() {
        if (album == null) return;

        if (album.getUrlToImage() != null) {
            getView().setImage(album.getUrlToImage());
        }

        if (album.getName() != null)
            getView().setName(album.getName());

        getView().setListeners(album.getPlayCount());
    }

    @Override
    public void setAlbum(Album artist) {
        this.album = artist;
    }

}
