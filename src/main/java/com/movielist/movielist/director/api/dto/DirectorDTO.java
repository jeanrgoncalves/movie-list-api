package com.movielist.movielist.director.api.dto;

import com.movielist.movielist.genericentitydto.DataTransferObject;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DirectorDTO extends DataTransferObject {

    private UUID id;
    private String name;

}
