package com.example.alexey.bestoflastfm.ui.list_artists;


import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.base.BasePresenter;
import com.example.alexey.bestoflastfm.ui.base.BaseView;

import java.util.List;

public interface ArtistsMVPContract {

    interface View extends BaseView {

        void toggleLoading(boolean visible);

        void updateListContent(List<Artist> artists);

        void setTitle(String title);

    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        OnClick<Artist> getOnItemClick();

        void showChangeCountryDialog();
    }

}
