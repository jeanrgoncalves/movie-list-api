package com.movielist.movielist.movie.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;


    public List<Movie> findAll(){
        return repository.findAll();
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
