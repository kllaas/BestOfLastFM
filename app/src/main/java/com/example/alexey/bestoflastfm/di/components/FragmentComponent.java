package com.example.alexey.bestoflastfm.di.components;

import com.example.alexey.bestoflastfm.di.PerFragment;
import com.example.alexey.bestoflastfm.di.modules.FragmentModule;
import com.example.alexey.bestoflastfm.ui.details_album.DetailsAlbumFragment;
import com.example.alexey.bestoflastfm.ui.details_artist.DetailsArtistFragment;
import com.example.alexey.bestoflastfm.ui.list_artists.ArtistsFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ArtistsFragment fragment);

    void inject(DetailsArtistFragment fragment);

    void inject(DetailsAlbumFragment fragment);

}
