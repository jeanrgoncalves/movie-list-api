package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.actor.api.dto.ActorDTO;
import com.movielist.movielist.actor.api.dto.ActorDTOAssembler;
import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.director.api.dto.DirectorDTO;
import com.movielist.movielist.director.api.dto.DirectorDTOAssembler;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.movieactor.api.dto.MovieActorDTO;
import com.movielist.movielist.movie.movieactor.api.dto.MovieActorDTOAssembler;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class MovieDtoAssemblerTest {

    @SpyBean
    private MovieDTOAssembler movieDTOAssembler;

    @SpyBean
    private DirectorDTOAssembler directorDTOAssembler;

    @SpyBean
    private MovieActorDTOAssembler movieActorDTOAssembler;

    @SpyBean
    private ActorDTOAssembler actorDTOAssembler;

    @Test
    public void fromEntityWithDirectorAndCast() {
        UUID movieId = UUID.randomUUID();

        Movie entity = Movie.builder()
                .id(movieId)
                .name("Em Ritmo de Fuga")
                .alreadySeen(true)
                .director(Director.builder()
                        .id(UUID.randomUUID())
                        .name("Edgar Wright")
                        .build())
                .cast(Collections.singletonList(MovieActor.builder()
                        .movie(Movie.builder().id(movieId).build())
                        .actor(Actor.builder()
                                .name("Ansel Elgort")
                                .build())
                        .build()))
                .build();

        MovieDTO dto = movieDTOAssembler.fromEntity(entity);
        Assert.assertNotNull("Diretor não deveria estar nulo no dto pois não está nulo na entidade", dto.getDirectorDTO());
        Assert.assertEquals("Lista de elenco não deveria estar vazia no dto pois não está nula nem vazia na entidade", 1, dto.getCastDTO().size());
    }

    @Test
    public void fromEntityWithNullDirectorAndNullCast() {
        UUID movieId = UUID.randomUUID();

        Movie entity = Movie.builder()
                .id(movieId)
                .name("Em Ritmo de Fuga")
                .alreadySeen(true)
                .build();

        MovieDTO dto = movieDTOAssembler.fromEntity(entity);
        Assert.assertNull("Diretor deveria estar nulo no dto pois está nulo na entidade", dto.getDirectorDTO());
        Assert.assertEquals("Lista de elenco deveria estar vazia no dto pois está nula na entidade", 0, dto.getCastDTO().size());
    }

    @Test
    public void fromDTOWithDirectorAndCast() {
        UUID movieId = UUID.randomUUID();

        MovieDTO dto = MovieDTO.builder()
                .id(movieId)
                .name("A Bruxa")
                .alreadySeen(true)
                .directorDTO(DirectorDTO.builder()
                        .id(UUID.randomUUID())
                        .name("Robert Eggers")
                        .build())
                .castDTO(Collections.singletonList(MovieActorDTO.builder()
                        .movieId(movieId)
                        .actorDTO(ActorDTO.builder()
                                .name("Anya Taylor-Joy")
                                .build())
                        .build()))
                .build();

        Movie entity = movieDTOAssembler.fromDTO(dto);
        Assert.assertNotNull("Diretor não deveria estar nulo na entidade pois não está nulo no dto", entity.getDirector());
        Assert.assertEquals("Lista de elenco não deveria estar vazia na entidade pois não está nula nem vazia no dto", 1, entity.getCast().size());
    }

    @Test
    public void fromDTOWithNullDirectorAndNullCast() {
        UUID movieId = UUID.randomUUID();

        MovieDTO dto = MovieDTO.builder()
                .id(movieId)
                .name("A Bruxa")
                .alreadySeen(true)
                .build();

        Movie entity = movieDTOAssembler.fromDTO(dto);
        Assert.assertNull("Diretor deveria estar nulo na entidade pois está nulo no dto", entity.getDirector());
        Assert.assertEquals("Lista de elenco deveria estar vazia na entidade pois está nula no dto", 0, entity.getCast().size());
    }

}
