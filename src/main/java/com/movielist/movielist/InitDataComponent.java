package com.movielist.movielist.movie;

import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDataComponent {

    @Autowired
    MovieRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() throws Exception {
        Movie inception = Movie.builder()
                .name("Inception")
                .alreadySeen(true)
                .build();

        Movie pearlHarbor = Movie.builder()
                .name("Pearl Harbor")
                .alreadySeen(true)
                .build();

        Movie theIrishman = Movie.builder()
                .name("The Irishman")
                .alreadySeen(false)
                .build();

        repository.saveAll(Arrays.asList(inception, pearlHarbor, theIrishman));
    }

}
