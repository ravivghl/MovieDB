package com.moviedbdemo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonResponse implements Serializable
{

    private Integer page;
    private Integer totalResults;
    private Integer totalPages;
    private ArrayList<MovieModel> results = null;
    private final static long serialVersionUID = 463478823370456371L;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }

}
