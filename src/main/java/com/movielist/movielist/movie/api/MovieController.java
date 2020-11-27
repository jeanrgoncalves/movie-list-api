package com.movielist.movielist.movie.api;

import com.movielist.movielist.movie.api.dto.MovieDTO;
import com.movielist.movielist.movie.api.dto.MovieDTOAssembler;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/movies")
@Api(value = "Movie")
public class MovieController {

    @Autowired
    private MovieDTOAssembler assembler;

    @Autowired
    private MovieService service;

    @GetMapping
    @ApiOperation(value = "Find all Movies")
    public ResponseEntity<List<MovieDTO>> findAll() {
        var movies = service.findAll();
        var dtos = assembler.assembleManyDTOs(movies);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping (path = "/namePart/{namePart}")
    @ApiOperation(value = "Find Movies by part of name")
    public ResponseEntity<List<MovieDTO>> findByNameContaining(@PathVariable String namePart) {
            var movies = service.findByNamePart(namePart);
            var dtos = assembler.assembleManyDTOs(movies);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Save a Movie")
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        Movie movie = service.save(assembler.fromDTO(movieDTO));
        MovieDTO dto = assembler.fromEntity(movie);
        return new ResponseEntity<>(dto, movieDTO.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a Movie by id")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }

}
