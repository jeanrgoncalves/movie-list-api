package com.movielist.movielist;

import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.actor.domain.ActorRepository;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.director.domain.DirectorRepository;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieService;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitDataComponent {

    @Autowired
    MovieService movieService;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    ActorRepository actorRepository;

    //@EventListener(ApplicationReadyEvent.class) //dados para teste de performance
    public void initData() {
        for (int i = 0; i < 3000; i++) {
            String index = String.valueOf(i);

            Director director = Director.builder().name("Dir Perf " + index).build();
            directorRepository.save(director);

            Actor actor = Actor.builder().name("Actor Perf" + index).build();
            actorRepository.save(actor);

            List<MovieActor> cast = Arrays.asList(MovieActor.builder()
                    .actor(actor)
                    .build());

            Movie movie = Movie.builder()
                    .name("Movie Perf" + index)
                    .alreadySeen(true)
                    .director(director)
                    .cast(cast)
                    .build();

            movieService.save(movie);
        }
    }

}
