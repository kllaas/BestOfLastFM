package com.example.alexey.bestoflastfm.di.modules;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.di.PerFragment;
import com.example.alexey.bestoflastfm.ui.details_album.DetailsAlbumMVPContract;
import com.example.alexey.bestoflastfm.ui.details_album.DetailsAlbumPresenter;
import com.example.alexey.bestoflastfm.ui.details_artist.DetailsArtistMVPContract;
import com.example.alexey.bestoflastfm.ui.details_artist.DetailsArtistPresenter;
import com.example.alexey.bestoflastfm.ui.list_artists.ArtistsMVPContract;
import com.example.alexey.bestoflastfm.ui.list_artists.ArtistsPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
@PerFragment
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    ArtistsMVPContract.Presenter<ArtistsMVPContract.View> provideListPresenter(
            ArtistsPresenter<ArtistsMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    Context provideContext() {
        return fragment.getContext();
    }

    @Provides
    @PerFragment
    FragmentManager provideChildFragmentManager() {
        return fragment.getChildFragmentManager();
    }

    @Provides
    List<Artist> provideArtists() {
        return new ArrayList<>();
    }

    @Provides
    List<Album> provideAlbums() {
        return new ArrayList<>();
    }

    @Provides
    DetailsArtistMVPContract.Presenter<DetailsArtistMVPContract.View> provideDetailsPresenter(
            DetailsArtistPresenter<DetailsArtistMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    DetailsAlbumMVPContract.Presenter<DetailsAlbumMVPContract.View> provideAlbumPresenter(
            DetailsAlbumPresenter<DetailsAlbumMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    FragmentManager provideFragmentManager() {
        return fragment.getChildFragmentManager();
    }
}
