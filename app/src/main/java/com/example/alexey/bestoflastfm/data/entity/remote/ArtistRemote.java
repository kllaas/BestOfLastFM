package com.example.alexey.bestoflastfm.data.entity.remote;

import java.util.List;

public class ArtistRemote {

    private String name;

    private String listeners;

    private String url;

    private List<ImageRemote> image = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageRemote> getImages() {
        return image;
    }

    public void setImages(List<ImageRemote> images) {
        this.image = images;
    }

}