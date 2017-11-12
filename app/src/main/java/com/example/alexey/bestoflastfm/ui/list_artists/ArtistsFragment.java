package com.example.alexey.bestoflastfm.ui.list_artists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.di.components.FragmentComponent;
import com.example.alexey.bestoflastfm.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by alexey
 */

public class ArtistsFragment extends BaseFragment implements ArtistsMVPContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Inject
    ArtistsMVPContract.Presenter<ArtistsMVPContract.View> presenter;

    @Inject
    ArtistsAdapter artistsAdapter;

    public static ArtistsFragment newInstance() {
        Bundle args = new Bundle();

        ArtistsFragment fragment = new ArtistsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.artists_list, container, false);

        FragmentComponent component = getComponent();
        component.inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    protected void setUpViews() {
        presenter.takeView(this);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(artistsAdapter);

        artistsAdapter.setOnItemClick(presenter.getOnItemClick());

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void toggleLoading(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateListContent(List<Artist> artists) {
        artistsAdapter.updateDataSet(artists);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_artist_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_country:
                presenter.showChangeCountryDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
