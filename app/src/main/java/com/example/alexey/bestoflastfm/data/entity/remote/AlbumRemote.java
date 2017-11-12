package com.example.alexey.bestoflastfm.data.entity.remote;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumRemote {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("playcount")
    @Expose
    private Integer playcount;

    @SerializedName("image")
    @Expose
    private List<ImageRemote> image = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public List<ImageRemote> getImage() {
        return image;
    }

    public void setImage(List<ImageRemote> image) {
        this.image = image;
    }

}