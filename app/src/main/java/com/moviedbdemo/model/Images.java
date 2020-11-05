package com.moviedbdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Images implements Serializable
{

    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("secure_base_url")
    @Expose
    private String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    @Expose
    private ArrayList<String> backdropSizes = null;
    @SerializedName("logo_sizes")
    @Expose
    private ArrayList<String> logoSizes = null;
    @SerializedName("poster_sizes")
    @Expose
    private ArrayList<String> posterSizes = null;
    @SerializedName("profile_sizes")
    @Expose
    private ArrayList<String> profileSizes = null;
    @SerializedName("still_sizes")
    @Expose
    private ArrayList<String> stillSizes = null;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public ArrayList<String> getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(ArrayList<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public ArrayList<String> getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(ArrayList<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public ArrayList<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(ArrayList<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public ArrayList<String> getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(ArrayList<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public ArrayList<String> getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(ArrayList<String> stillSizes) {
        this.stillSizes = stillSizes;
    }


}