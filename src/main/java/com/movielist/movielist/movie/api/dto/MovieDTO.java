package com.movielist.movielist.movie.api.dto;

import com.movielist.movielist.director.api.dto.DirectorDTO;
import com.movielist.movielist.genericentitydto.DataTransferObject;
import lombok.*;

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

}
