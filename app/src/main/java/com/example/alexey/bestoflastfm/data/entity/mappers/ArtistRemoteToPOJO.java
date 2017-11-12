package com.example.alexey.bestoflastfm.data.entity.mappers;

import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.data.entity.remote.ArtistRemote;
import com.example.alexey.bestoflastfm.data.entity.remote.ImageRemote;

import java.util.ArrayList;
import java.util.List;

public class ArtistRemoteToPOJO {

    public static List<Artist> transformList(List<ArtistRemote> remoteArtists, String country) {
        List<Artist> artists = new ArrayList<>();

        for (ArtistRemote item : remoteArtists) {
            artists.add(transformItem(item, country));
        }

        return artists;
    }

    private static Artist transformItem(ArtistRemote item, String country) {
        Artist artist = new Artist();

        artist.setName(item.getName());
        artist.setUrlToImage(getBiggestImage(item.getImages()));
        artist.setListeners(Integer.parseInt(item.getListeners()));
        artist.setCountry(country);

        return artist;
    }

    private static String getBiggestImage(List<ImageRemote> images) {
        if (images.size() == 0) return "";

        return images.get(images.size() - 1).getText();
    }

}
