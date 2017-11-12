package com.example.alexey.bestoflastfm.data.entity.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.alexey.bestoflastfm.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.DataBase.ARTISTS_DB_NAME)
public class Artist implements Serializable {

    @PrimaryKey
    private String name;

    private int listeners;

    private String urlToImage;

    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

}
