package com.movielist.movielist.tmdb.domain.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TMDBTopRatedMoviesResponseResult {

    private Integer id;
    private String title;
    private String original_title;
    private String overview;

}
