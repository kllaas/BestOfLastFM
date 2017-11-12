package com.example.alexey.bestoflastfm.data.entity.mappers;

import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.remote.AlbumRemote;
import com.example.alexey.bestoflastfm.data.entity.remote.ImageRemote;

import java.util.ArrayList;
import java.util.List;

public class AlbumRemoteToPOJO {

    public static List<Album> transformList(List<AlbumRemote> remoteArtists, String artistName) {
        List<Album> artists = new ArrayList<>();

        for (AlbumRemote item : remoteArtists) {
            artists.add(transformItem(item, artistName));
        }

        return artists;
    }

    private static Album transformItem(AlbumRemote item, String artistName) {
        Album album = new Album();

        album.setName(item.getName());
        album.setUrlToImage(getBiggestImage(item.getImage()));
        album.setPlayCount(item.getPlaycount());
        album.setArtistName(artistName);

        return album;
    }

    private static String getBiggestImage(List<ImageRemote> images) {
        if (images.size() == 0) return "";

        return images.get(images.size() - 1).getText();
    }

}
