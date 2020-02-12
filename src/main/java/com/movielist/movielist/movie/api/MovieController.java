package com.movielist.movielist.movie.api;

import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        var movies = repository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> insert(@RequestBody Movie movie) {
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

}
