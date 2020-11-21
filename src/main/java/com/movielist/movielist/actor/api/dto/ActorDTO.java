package com.movielist.movielist.actor.api.dto;

import com.movielist.movielist.genericentitydto.DataTransferObject;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ActorDTO extends DataTransferObject {

    private UUID id;
    private String name;
    private LocalDate birthDate;

}
