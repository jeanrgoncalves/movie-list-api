package com.movielist.movielist.tmdb.domain.movie;

import com.movielist.movielist.tmdb.rest.TMDBAbstractResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TMDMTopRatedMoviesResponse extends TMDBAbstractResponse {

    private Integer page;
    private Integer total_pages;
    private List<TMDBTopRatedMoviesResponseResult> results;
    private Integer total_results;

}
