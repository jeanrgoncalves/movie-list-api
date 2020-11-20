package com.movielist.movielist.director.api.dto;

import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.genericentitydto.AbstractAssembler;
import org.springframework.stereotype.Component;

@Component
public class DirectorDTOAssembler extends AbstractAssembler<Director, DirectorDTO> {

    @Override
    public DirectorDTO fromEntity(Director entity) {
        return DirectorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Director fromDTO(DirectorDTO dto) {
        return Director.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
