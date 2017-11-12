package com.example.alexey.bestoflastfm.ui.details_artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.ui.base.adapter.BaseRecyclerViewAdapter;
import com.example.alexey.bestoflastfm.ui.base.adapter.BaseViewHolder;
import com.example.alexey.bestoflastfm.utils.widgets.SquareImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alexey
 */

public class AlbumsAdapter extends BaseRecyclerViewAdapter<Album, AlbumsAdapter.ViewHolder> {

    @Inject
    AlbumsAdapter(List<Album> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false));
    }

    class ViewHolder extends BaseViewHolder<Album> {

        @BindView(R.id.image)
        SquareImageView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.play_count)
        TextView playCount;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Album album) {
            name.setText(album.getName());

            String listenersFormatted = itemView.getContext()
                    .getString(R.string.listeners_count, album.getPlayCount());
            this.playCount.setText(listenersFormatted);

            if (album.getUrlToImage() != null )
                Glide.with(itemView.getContext())
                        .load(album.getUrlToImage())
                        .asBitmap()
                        .fitCenter()
                        .into(image);

            itemView.setOnClickListener(v -> onClick.onClick(album));
        }
    }

}
