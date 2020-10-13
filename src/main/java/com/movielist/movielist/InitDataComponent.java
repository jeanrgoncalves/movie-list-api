package com.movielist.movielist;

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
    public void initData() {
        Movie inception = Movie.builder()
                .name("A Origem")
                .alreadySeen(true)
                .build();

        Movie pearlHarbor = Movie.builder()
                .name("Pearl Harbor")
                .alreadySeen(true)
                .build();

        Movie theIrishman = Movie.builder()
                .name("O Irlandês")
                .alreadySeen(false)
                .build();

        repository.saveAll(Arrays.asList(inception, pearlHarbor, theIrishman));
    }

}
