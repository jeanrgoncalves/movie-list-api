package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.director.api.dto.DirectorDTO;
import com.movielist.movielist.director.api.dto.DirectorDTOAssembler;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import com.movielist.movielist.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieDTOAssembler extends AbstractAssembler<Movie, MovieDTO> {

    @Autowired
    private DirectorDTOAssembler directorDTOAssembler;

    @Override
    public MovieDTO fromEntity(Movie entity) {
        DirectorDTO directorDTO = entity.getDirector() != null
                ? directorDTOAssembler.fromEntity(entity.getDirector())
                :null;

        return MovieDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .directorDTO(directorDTO)
                .alreadySeen(entity.isAlreadySeen())
                .build();
    }

    @Override
    public Movie fromDTO(MovieDTO dto) {
        Director director = dto.getDirectorDTO() != null
                ? directorDTOAssembler.fromDTO(dto.getDirectorDTO())
                :null;

        return Movie.builder()
                .id(dto.getId())
                .director(director)
                .name(dto.getName())
                .alreadySeen(dto.isAlreadySeen())
                .build();
    }
}
