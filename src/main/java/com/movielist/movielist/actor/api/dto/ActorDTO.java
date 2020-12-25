package com.movielist.movielist.actor.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movielist.movielist.genericentitydto.DataTransferObject;
import com.movielist.movielist.util.ConstantsFormats;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ActorDTO extends DataTransferObject {

    private UUID id;
    private String name;

    @JsonFormat(pattern = ConstantsFormats.DATE_TIME_API_FORMAT)
    private LocalDateTime birthDate;

}
