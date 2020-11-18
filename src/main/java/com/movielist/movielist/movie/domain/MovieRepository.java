package com.movielist.movielist.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    List<Movie> findByNameAndDirectorId(String name, UUID directorId); //retorno em lista para evitar exception caso tenha sido inserido duplicado direto no banco

}
