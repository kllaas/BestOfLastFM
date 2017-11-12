package com.example.alexey.bestoflastfm.ui.details_album;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.di.components.DaggerFragmentComponent;
import com.example.alexey.bestoflastfm.di.modules.FragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsAlbumFragment extends DialogFragment
        implements DetailsAlbumMVPContract.View {

    private static final String ALBUM_BUNDLE_KEY = "album_bundle";

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.listeners)
    TextView listeners;

    @BindView(R.id.name)
    TextView name;

    @Inject
    DetailsAlbumMVPContract.Presenter<DetailsAlbumMVPContract.View> presenter;

    public static DetailsAlbumFragment newInstance(Album album) {
        Bundle args = new Bundle();

        args.putSerializable(ALBUM_BUNDLE_KEY, album);

        DetailsAlbumFragment fragment = new DetailsAlbumFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFragmentComponent.builder()
                .applicationComponent(App.appComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);

        Album album = (Album) getArguments().getSerializable(ALBUM_BUNDLE_KEY);
        presenter.setAlbum(album);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.detail_album, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setNeutralButton(getActivity().getString(R.string.close), (dialogInterface, i) -> {
            dialogInterface.cancel();
        });

        presenter.takeView(this);

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.dropView();
    }

    @Override
    public void setName(String text) {
        name.setText(text);
    }

    @Override
    public void setImage(String imageUrl) {
        toggleProgressBar(true);

        Glide.with(getContext())
                .load(imageUrl)
                .asBitmap()
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        image.setImageBitmap(resource);
                        toggleProgressBar(false);
                    }
                });
    }

    @Override
    public void setListeners(int count) {
        listeners.setText(String.format(getString(R.string.listeners_count), count));
    }

    @Override
    public void toggleProgressBar(boolean visibility) {
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }
}
