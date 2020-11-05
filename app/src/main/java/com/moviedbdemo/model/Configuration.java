package com.moviedbdemo.model;

import java.io.Serializable;

public class Configuration implements Serializable
{

    private Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}