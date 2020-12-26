package com.movielist.movielist.movie.validator;

import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import com.movielist.movielist.util.Translator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class RepeatedActorValidatorTest {

    private Movie movieWithRepeatedActor;
    private Movie movieWithNoRepeatedActor;

    @SpyBean
    private RepeatedActorValidator validator;

    @SpyBean
    private Translator translator;


    Actor liamNeeson = Actor.builder()
            .id(UUID.randomUUID())
            .name("Liam Neeson")
            .build();

    Actor tomHanks = Actor.builder()
            .id(UUID.randomUUID())
            .name("Tom Hanks")
            .build();

    Actor alanRickman = Actor.builder()
            .id(UUID.randomUUID())
            .name("Alan Rickman")
            .build();

    Director jordanPeele = Director.builder()
            .id(UUID.randomUUID())
            .name("Jordan Peele")
            .build();


    @Test
    public void movieWithRepeatedActor() {
        Movie movie = Movie.builder()
                .id(UUID.randomUUID())
                .name("Repeated Actor")
                .build();

        movie.addActor(MovieActor.builder()
                .actor(liamNeeson)
                .build());

        movie.addActor(MovieActor.builder()
                .actor(tomHanks)
                .build());

        movie.addActor(MovieActor.builder()
                .actor(alanRickman)
                .build());

        movie.addActor(MovieActor.builder()
                .actor(liamNeeson)
                .build());

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals("Deveria retornar 1 erro pelo ator Liam Neeson se repetir no elenco", 1, validationMsgs.size());
    }

    @Test
    public void movieWithNoRepeatedActor() {
        Movie movie = Movie.builder()
                .id(UUID.randomUUID())
                .name("No Repeated Actor")
                .build();

        movie.addActor(MovieActor.builder()
                .actor(liamNeeson)
                .build());

        movie.addActor(MovieActor.builder()
                .actor(tomHanks)
                .build());

        movie.addActor(MovieActor.builder()
                .actor(alanRickman)
                .build());

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals(" Não deveria retornar nenhum erro pois nenhum ator se repete no elenco", 0, validationMsgs.size());
    }

}
