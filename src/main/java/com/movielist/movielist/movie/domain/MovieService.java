package com.movielist.movielist.movie.domain;

import com.movielist.movielist.apierror.ApiError;
import com.movielist.movielist.apierror.CustomException;
import com.movielist.movielist.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;


    public List<Movie> findAll() {
        return repository.findAll();
    }

    public List<Movie> findByNamePart(String namePart) {
        final String normalizedNamePart = StringUtil.upperCaseAndRemoveAccents(namePart);

        return repository.findAll().stream()
                .filter(movie -> {
                    String movieName = StringUtil.upperCaseAndRemoveAccents(movie.getName());
                    return movieName.contains(normalizedNamePart);
                })
                .collect(Collectors.toList());
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void deleteById(UUID id) {
       try {
           repository.deleteById(id);
       } catch (Exception e) {
           throw CustomException.builder()
                   .message("Filme com ID " + id.toString() + " não encontrado.")
                   .build();
       }
    }

}
