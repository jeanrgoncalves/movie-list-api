package com.movielist.movielist.actor.api;

import com.movielist.movielist.actor.api.dto.ActorDTO;
import com.movielist.movielist.actor.api.dto.ActorDTOAssembler;
import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.actor.domain.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/actors")
@Api(value = "Actor")
public class ActorController {

    @Autowired
    ActorDTOAssembler assembler;

    @Autowired
    ActorService service;

    @PostMapping
    @ApiOperation(value = "Save an Actor")
    public ResponseEntity<ActorDTO> save(@RequestBody ActorDTO actorDTO) {
        Actor actor = service.save(assembler.fromDTO(actorDTO));
        ActorDTO dto = assembler.fromEntity(actor);
        return new ResponseEntity<>(dto, actorDTO.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
    }

}
