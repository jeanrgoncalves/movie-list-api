package com.movielist.movielist.genericentitydto;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAssembler <ENTITY extends BaseEntity, DTO extends DataTransferObject> {

    public abstract DTO fromEntity(ENTITY entity);
    public abstract ENTITY fromDTO(DTO dto);

    public List<DTO> assembleManyDTOs(List<ENTITY> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ENTITY> assembleManyEntities(List<DTO> dtos) {
        return dtos.stream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
    }

}
