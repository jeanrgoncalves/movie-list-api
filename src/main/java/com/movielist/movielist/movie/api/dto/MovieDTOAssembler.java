package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.director.api.dto.DirectorDTO;
import com.movielist.movielist.director.api.dto.DirectorDTOAssembler;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.movieactor.api.dto.MovieActorDTO;
import com.movielist.movielist.movie.movieactor.api.dto.MovieActorDTOAssembler;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDTOAssembler extends AbstractAssembler<Movie, MovieDTO> {

    @Autowired
    private DirectorDTOAssembler directorDTOAssembler;

    @Autowired
    private MovieActorDTOAssembler movieActorDTOAssembler;

    @Override
    public MovieDTO fromEntity(Movie entity) {
        DirectorDTO directorDTO = entity.getDirector() != null
                ? directorDTOAssembler.fromEntity(entity.getDirector())
                : null;

        List<MovieActorDTO> castDTO = entity.getCast() != null
                ? movieActorDTOAssembler.assembleManyDTOs(entity.getCast())
                : new ArrayList<>();

        return MovieDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .directorDTO(directorDTO)
                .alreadySeen(entity.isAlreadySeen())
                .castDTO(castDTO)
                .build();
    }

    @Override
    public Movie fromDTO(MovieDTO dto) {
        Director director = dto.getDirectorDTO() != null
                ? directorDTOAssembler.fromDTO(dto.getDirectorDTO())
                : null;

        List<MovieActor> cast = dto.getCastDTO() != null
                ? movieActorDTOAssembler.assembleManyEntities(dto.getCastDTO())
                : new ArrayList<>();

        return Movie.builder()
                .id(dto.getId())
                .director(director)
                .name(dto.getName())
                .alreadySeen(dto.isAlreadySeen())
                .cast(cast)
                .build();
    }
}
