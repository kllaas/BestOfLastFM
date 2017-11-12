package com.example.alexey.bestoflastfm.ui.list_artists;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.ui.base.BasePresenterImpl;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class ArtistsPresenter<V extends ArtistsMVPContract.View>
        extends BasePresenterImpl<V> implements ArtistsMVPContract.Presenter<V>, OnClick<Artist> {

    private String currCountry = "Ukraine";

    private Context context;

    @Inject
    ArtistsPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                     CompositeDisposable compositeDisposable, NavigationManager navigationManager,
                     Context context) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);

        this.context = context;
    }

    @Override
    protected void onViewPrepared() {
        loadArtists();

        updateTitle();
    }

    private void updateTitle() {
        getView().setTitle(String.format(context.getString(R.string.the_best_in_), currCountry));
    }

    private void loadArtists() {
        getView().toggleLoading(true);

        getCompositeDisposable().add(getRepository()
                .fetchArtists(currCountry)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(unsortedList -> {

                    Collections.sort(unsortedList, (a1, a2) -> a1.getName().compareTo(a2.getName()));

                }).subscribe(this::onArtistsLoaded,
                        throwable -> onLoadingFailed(throwable.getLocalizedMessage()))
        );
    }

    private void onLoadingFailed(String localizedMessage) {
        Log.d("onLoadingFailed", localizedMessage);
        getView().toggleLoading(false);
    }

    private void onArtistsLoaded(List<Artist> artists) {
        if (artists != null && artists.size() != 0) {
            getView().updateListContent(artists);
        }

        getView().toggleLoading(false);
    }

    @Override
    public void onClick(Artist artist) {
        App.appComponent.getNavigationManager().openDetailsArtistFragment(artist);
    }

    @Override
    public OnClick<Artist> getOnItemClick() {
        return this;
    }

    @Override
    public void showChangeCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Specially hardcoded
        List<String> counties = new ArrayList<String>() {{
            add("Ukraine");
            add("China");
            add("Iceland");
        }};

        int selected = counties.indexOf(currCountry);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.item_country, counties);

        builder.setTitle(context.getResources().getString(R.string.choose_country));
        builder.setSingleChoiceItems(adapter, selected, (dialog, item) -> {
            onCountryChanged(counties.get(item));
            dialog.cancel();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onCountryChanged(String animal) {
        currCountry = animal;

        updateTitle();
        loadArtists();
    }
}
