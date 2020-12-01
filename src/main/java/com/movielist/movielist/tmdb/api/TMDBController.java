package com.movielist.movielist.tmdb.api;

import com.movielist.movielist.tmdb.domain.movie.TMDMTopRatedMoviesResponse;
import com.movielist.movielist.tmdb.rest.TMDBRestApiConfiguration;
import com.movielist.movielist.tmdb.rest.TMDBRestRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/tmdb")
public class TMDBController {

    @Autowired
    private TMDBRestRequest tmdbRestRequest;

    @GetMapping(path = "/movie/top_rated")
    @ApiOperation(value = "Find TMDB Top Rated Movies")
    public ResponseEntity<TMDMTopRatedMoviesResponse> findTopRatedMovies() {
        String urlParams = "?language={languageValue}";
        Map<String, String> paramsValues = new HashMap<>();
        paramsValues.put("languageValue", "pt-BR");

        var response = tmdbRestRequest.request(TMDBRestApiConfiguration.URL_TOP_RATED_MOVIES,
                urlParams, paramsValues, HttpMethod.GET, null, TMDMTopRatedMoviesResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
