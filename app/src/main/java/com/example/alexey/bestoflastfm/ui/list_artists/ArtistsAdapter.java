package com.example.alexey.bestoflastfm.ui.list_artists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.base.adapter.BaseRecyclerViewAdapter;
import com.example.alexey.bestoflastfm.ui.base.adapter.BaseViewHolder;
import com.example.alexey.bestoflastfm.utils.widgets.SquareImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alexey
 */

public class ArtistsAdapter extends BaseRecyclerViewAdapter<Artist, ArtistsAdapter.ViewHolder> {

    @Inject
    ArtistsAdapter(List<Artist> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artist, parent, false));
    }

    class ViewHolder extends BaseViewHolder<Artist> {

        @BindView(R.id.image)
        SquareImageView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.listeners)
        TextView listeners;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Artist artist) {
            name.setText(artist.getName());

            String listenersFormatted = itemView.getContext()
                    .getString(R.string.listeners_count, artist.getListeners());
            this.listeners.setText(listenersFormatted);

            if (artist.getUrlToImage() != null)
                Glide.with(itemView.getContext())
                        .load(artist.getUrlToImage())
                        .asBitmap()
                        .fitCenter()
                        .into(image);

            itemView.setOnClickListener(v -> onClick.onClick(artist));
        }
    }

}
