package com.example.alexey.bestoflastfm.ui.details_artist;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.di.components.FragmentComponent;
import com.example.alexey.bestoflastfm.ui.base.BaseFragment;
import com.example.alexey.bestoflastfm.utils.widgets.SimpleDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexey
 */

public class DetailsArtistFragment extends BaseFragment
        implements DetailsArtistMVPContract.View, AppBarLayout.OnOffsetChangedListener {

    private static final String ID_BUNDLE_KEY = "station_id";

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean isTitleVisible = false;
    private boolean isTitleContainerVisible = true;

    @BindView(R.id.main_linear_layout_title)
    LinearLayout titleContainer;

    @BindView(R.id.main_appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.placeholder)
    ImageView placeholder;

    @BindView(R.id.listeners)
    TextView listeners;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.name_largre)
    TextView nameLarge;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Inject
    AlbumsAdapter adapter;

    @Inject
    DetailsArtistMVPContract.Presenter<DetailsArtistMVPContract.View> presenter;

    public static DetailsArtistFragment newInstance(Artist artist) {
        Bundle args = new Bundle();

        args.putSerializable(ID_BUNDLE_KEY, artist);

        DetailsArtistFragment fragment = new DetailsArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_artist, container, false);

        FragmentComponent component = getComponent();
        component.inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        Artist artist = (Artist) getArguments().getSerializable(ID_BUNDLE_KEY);
        presenter.setArtist(artist);

        return view;
    }

    @Override
    protected void setUpViews() {
        presenter.takeView(this);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        adapter.setOnItemClick(presenter.getOnItemClick());

        appBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(name, 0, View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void setName(String text) {
        name.setText(text);
        nameLarge.setText(text);
    }

    @Override
    public void setListeners(int count) {
        listeners.setText(String.format(getString(R.string.listeners_count), count));
    }

    @Override
    public void toggleProgressBar(boolean visibility) {
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setImage(String imageUrl) {
        Glide.with(getContext())
                .load(imageUrl)
                .asBitmap()
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (image == null) return;

                        image.setImageBitmap(resource);
                        placeholder.setImageBitmap(resource);
                    }
                });
    }
    @Override
    public void updateDataSet(List<Album> albums) {
        adapter.updateDataSet(albums);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    @OnClick(R.id.back)
    void onClick() {
        getActivity().onBackPressed();
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!isTitleVisible) {
                startAlphaAnimation(name, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                isTitleVisible = true;
            }

        } else {

            if (isTitleVisible) {
                startAlphaAnimation(name, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                isTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {

            if(isTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                isTitleContainerVisible = false;
            }

        } else {

            if (!isTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                isTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
