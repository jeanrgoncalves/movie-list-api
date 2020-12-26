package com.movielist.movielist.movie.movieactor.api.dto;

import com.movielist.movielist.actor.api.dto.ActorDTOAssembler;
import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MovieActorDTOAssembler extends AbstractAssembler<MovieActor, MovieActorDTO> {

    @Autowired
    private ActorDTOAssembler actorDTOAssembler;

    @Override
    public MovieActorDTO fromEntity(MovieActor entity) {
        return MovieActorDTO.builder()
                .id(entity.getId())
                .movieId(entity.getMovie().getId())
                .actorDTO(actorDTOAssembler.fromEntity(entity.getActor()))
                .build();
    }

    @Override
    public MovieActor fromDTO(MovieActorDTO dto) {
        UUID actorID = dto.getActorDTO() != null
                ? dto.getActorDTO().getId()
                : null;

        String actorName = dto.getActorDTO() != null
                ? dto.getActorDTO().getName()
                : null;

        return MovieActor.builder()
                .id(dto.getId())
                .movie(Movie.builder().id(dto.getMovieId()).build())
                .actor(Actor.builder().id(actorID).name(actorName).build())
                .build();
    }
}
