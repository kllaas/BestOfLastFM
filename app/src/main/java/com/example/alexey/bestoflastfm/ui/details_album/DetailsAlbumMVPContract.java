package com.example.alexey.bestoflastfm.ui.details_album;


import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.ui.base.BasePresenter;
import com.example.alexey.bestoflastfm.ui.base.BaseView;

public interface DetailsAlbumMVPContract {

    interface View extends BaseView {

        void setName(String text);

        void setImage(String imageUrl);

        void setListeners(int count);

        void toggleProgressBar(boolean visibility);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setAlbum(Album album);

    }

}
