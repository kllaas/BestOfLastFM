package com.example.alexey.bestoflastfm.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.bestoflastfm.ui.base.BasePresenter;
import com.example.alexey.bestoflastfm.ui.base.BaseView;

public interface MainMvpContract {

    interface View extends BaseView {

        FragmentManager getFragmentManger();
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void navigateBack(AppCompatActivity notesActivity);
    }

}
