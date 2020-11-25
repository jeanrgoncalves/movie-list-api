package com.movielist.movielist.movie.api;

import com.movielist.movielist.movie.api.dto.MovieDTO;
import com.movielist.movielist.movie.api.dto.MovieDTOAssembler;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieDTOAssembler assembler;

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll() {
        var movies = service.findAll();
        var dtos = assembler.assembleManyDTOs(movies);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping (path = "/namePart/{namePart}")
    public ResponseEntity<List<MovieDTO>> findByNameContaining(@PathVariable String namePart) {
            var movies = service.findByNamePart(namePart);
            var dtos = assembler.assembleManyDTOs(movies);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        Movie movie = service.save(assembler.fromDTO(movieDTO));
        MovieDTO dto = assembler.fromEntity(movie);
        return new ResponseEntity<>(dto, movieDTO.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable UUID id) {
        service.deleteById(id);
    }

}
