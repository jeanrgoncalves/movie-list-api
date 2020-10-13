package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.genericentitydto.AbstractAssembler;
import com.movielist.movielist.movie.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieDTOAssembler extends AbstractAssembler<Movie, MovieDTO> {

    @Override
    public MovieDTO fromEntity(Movie entity) {
        return MovieDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .alreadySeen(entity.isAlreadySeen())
                .build();
    }

    @Override
    public Movie fromDTO(MovieDTO dto) {
        return Movie.builder()
                .id(dto.getId())
                .name(dto.getName())
                .alreadySeen(dto.isAlreadySeen())
                .build();
    }
}
