package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.director.api.dto.DirectorDTO;
import com.movielist.movielist.genericentitydto.DataTransferObject;
import com.movielist.movielist.movie.movieactor.api.dto.MovieActorDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MovieDTO extends DataTransferObject {

    private UUID id;
    private String name;
    private boolean alreadySeen = false;
    private DirectorDTO directorDTO;
    private List<MovieActorDTO> castDTO = new ArrayList<>();

}
