package com.movielist.movielist.movie.movieactor.api.dto;

import com.movielist.movielist.actor.api.dto.ActorDTOAssembler;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import com.movielist.movielist.movie.api.dto.MovieDTOAssembler;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieActorDTOAssembler extends AbstractAssembler<MovieActor, MovieActorDTO> {

    @Autowired
    private MovieDTOAssembler movieDTOAssembler;

    @Autowired
    private ActorDTOAssembler actorDTOAssembler;

    @Override
    public MovieActorDTO fromEntity(MovieActor entity) {
        return MovieActorDTO.builder()
                .id(entity.getId())
                .movieDTO(movieDTOAssembler.fromEntity(entity.getMovie()))
                .actorDTO(actorDTOAssembler.fromEntity(entity.getActor()))
                .build();
    }

    @Override
    public MovieActor fromDTO(MovieActorDTO dto) {
        return MovieActor.builder()
                .id(dto.getId())
                .movie(movieDTOAssembler.fromDTO(dto.getMovieDTO()))
                .actor(actorDTOAssembler.fromDTO(dto.getActorDTO()))
                .build();
    }
}
