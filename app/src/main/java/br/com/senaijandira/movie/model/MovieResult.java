package br.com.senaijandira.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {

    //estado (atibutos da classe)
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    //gets and sets(comportamento da classe)

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
