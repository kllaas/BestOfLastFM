package com.example.alexey.bestoflastfm.ui.details_artist;


import android.support.v4.app.FragmentManager;

import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.base.BasePresenter;
import com.example.alexey.bestoflastfm.ui.base.BaseView;
import com.example.alexey.bestoflastfm.ui.list_artists.OnClick;

import java.util.List;

public interface DetailsArtistMVPContract {

    interface View extends BaseView {

        void setName(String text);

        void setImage(String imageUrl);

        void setListeners(int count);

        void toggleProgressBar(boolean visibility);

        void updateDataSet(List<Album> albums);

        FragmentManager getChildFragmentManager();
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setArtist(Artist artist);

        OnClick<Album> getOnItemClick();
    }

}
