package com.movielist.movielist.movie.validator;

import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieRepository;
import com.movielist.movielist.util.Translator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class MovieDirectorAlreadyExistsValidatorTest {

    @SpyBean
    private MovieDirectorAlreadyExistsValidator validator;

    @SpyBean
    private Translator translator;

    @MockBean
    private MovieRepository repository;

    private final String NON_EXISTING_MOVIE_NAME = "A Lista de Schindler";
    private final UUID NON_EXISTING_MOVIE_DIRECTOR_ID = UUID.fromString("65a0c966-dc7b-407c-82ce-4c327664d6c8");

    private final UUID EXISTING_MOVIE_ID = UUID.fromString("8382ebaf-69ac-43c6-8c0a-3409ddec14e6");
    private final String EXISTING_MOVIE_NAME = "A Vila";
    private final UUID EXISTING_MOVIE_DIRECTOR_ID = UUID.fromString("d0358597-7da7-4b97-b8f6-bb9c22d85f29");
    private final String EXISTING_MOVIE_DIRECTOR_NAME = "M. Night Shyamalan";

    @Before
    public void initMocks() {
        Mockito.when(repository.findByNameAndDirectorId(Mockito.eq(NON_EXISTING_MOVIE_NAME), Mockito.eq(NON_EXISTING_MOVIE_DIRECTOR_ID)))
                .thenReturn(new ArrayList<>());

        Movie foundMovie = Movie.builder()
                .id(EXISTING_MOVIE_ID)
                .name(EXISTING_MOVIE_NAME)
                .director(Director.builder()
                        .id(EXISTING_MOVIE_DIRECTOR_ID)
                        .name(EXISTING_MOVIE_DIRECTOR_NAME)
                        .build())
                .build();

        Mockito.when(repository.findByNameAndDirectorId(Mockito.eq(EXISTING_MOVIE_NAME), Mockito.eq(EXISTING_MOVIE_DIRECTOR_ID)))
                .thenReturn(Arrays.asList(foundMovie));
    }

    @Test
    public void notFoundMovieForNameAndDirector() {
        Movie movie = Movie.builder()
                .name(NON_EXISTING_MOVIE_NAME)
                .director(Director.builder()
                        .id(NON_EXISTING_MOVIE_DIRECTOR_ID)
                        .name("Steven Spielberg")
                        .build())
                .build();

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals("Não deveria retornar erro pois o mock retorna como se não tivesse encontrado nenhum filme", 0, validationMsgs.size());
    }

    @Test
    public void noIdAndMovieFoundForNameAndDirector() {
        Movie movie = Movie.builder()
                .name(EXISTING_MOVIE_NAME)
                .director(Director.builder()
                        .id(EXISTING_MOVIE_DIRECTOR_ID)
                        .name(EXISTING_MOVIE_DIRECTOR_NAME)
                        .build())
                .build();

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals("Deveria retornar 1 erro pois o mock retorna como se tivesse encontrado um filme", 1, validationMsgs.size());
    }

    @Test
    public void sameIdAndMovieFoundForNameAndDirector() {
        Movie movie = Movie.builder()
                .id(EXISTING_MOVIE_ID)
                .name(EXISTING_MOVIE_NAME)
                .director(Director.builder()
                        .id(EXISTING_MOVIE_DIRECTOR_ID)
                        .name(EXISTING_MOVIE_DIRECTOR_NAME)
                        .build())
                .build();

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals("Não deveria retornar erro pois o mock retorna como se tivesse encontrado um filme, porém com o mesmo ID do filme validado",
                0, validationMsgs.size());
    }

    @Test
    public void otherIdAndMovieFoundForNameAndDirector() {
        Movie movie = Movie.builder()
                .id(UUID.randomUUID())
                .name(EXISTING_MOVIE_NAME)
                .director(Director.builder()
                        .id(EXISTING_MOVIE_DIRECTOR_ID)
                        .name(EXISTING_MOVIE_DIRECTOR_NAME)
                        .build())
                .build();

        List<String> validationMsgs = validator.validate(movie);
        Assert.assertEquals("Deveria retornar 1 erro pois o mock retorna como se tivesse encontrado um filme com ID diferernte do filme validado",
                1, validationMsgs.size());
    }

}
