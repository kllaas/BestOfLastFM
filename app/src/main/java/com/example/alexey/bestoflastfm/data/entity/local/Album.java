package com.example.alexey.bestoflastfm.data.entity.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.alexey.bestoflastfm.utils.Constants;

import java.io.Serializable;

/**
 * Created by alexey
 */

@Entity(tableName = Constants.DataBase.ALBUMS_DB_NAME)
public class Album implements Serializable {

    @PrimaryKey
    private String name;

    private String artistName;

    private String urlToImage;

    private int playCount;

    @Ignore
    public Album() {}

    public Album(String name, String artistName, String urlToImage, int playCount) {
        this.name = name;
        this.artistName = artistName;
        this.urlToImage = urlToImage;
        this.playCount = playCount;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
