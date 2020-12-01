package com.movielist.movielist.tmdb.rest;

import lombok.Getter;

@Getter
public class TMDBRestRequestError extends TMDBAbstractResponse {

    private String status_message;
    private Integer status_code;
    private boolean success;

}
