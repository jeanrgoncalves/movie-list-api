package com.movielist.movielist.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    @Query("select distinct m from  Movie m " +
            "left join fetch m.director " +
            "left join fetch m.cast cast " +
            "left join fetch cast.actor " +
            "order by m.name")
    List<Movie> findAll();

    List<Movie> findByNameAndDirectorId(String name, UUID directorId); //retorno em lista para evitar exception caso tenha sido inserido duplicado direto no banco
}

