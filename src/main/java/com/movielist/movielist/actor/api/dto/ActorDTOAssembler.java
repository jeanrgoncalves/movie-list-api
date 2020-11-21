package com.movielist.movielist.actor.api.dto;

import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import org.springframework.stereotype.Component;

@Component
public class ActorDTOAssembler extends AbstractAssembler<Actor, ActorDTO> {

    @Override
    public ActorDTO fromEntity(Actor entity) {
        return ActorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .build();
    }

    @Override
    public Actor fromDTO(ActorDTO dto) {
        return Actor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
