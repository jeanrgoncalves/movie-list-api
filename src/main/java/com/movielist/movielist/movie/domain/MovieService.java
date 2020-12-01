package com.movielist.movielist.movie.domain;

import com.movielist.movielist.apierror.CustomException;
import com.movielist.movielist.movie.validator.MovieSaveValidator;
import com.movielist.movielist.util.StringUtil;
import com.movielist.movielist.util.Translator;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private Translator translator;

    private List<MovieSaveValidator> saveValidators;

    @Autowired
    public MovieService(ListableBeanFactory beanFactory) {
        saveValidators = new ArrayList<>(beanFactory.getBeansOfType(MovieSaveValidator.class).values());
    }

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
        setIDs(movie);

        List<String> validationMsgs = new ArrayList<>();

        saveValidators.forEach(validator -> {
            validationMsgs.addAll(validator.validate(movie));
        });

        if (!validationMsgs.isEmpty()) {
            throw new CustomException(translator.getText("saveMovieFailValidation"), validationMsgs);
        }

        return repository.save(movie);
    }

    private void setIDs(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(UUID.randomUUID());
        }

        UUID movieId = movie.getId();

        if (movie.getCast() != null) {
            movie.getCast().forEach(movieActor -> {
                movieActor.setMovie(Movie.builder().id(movieId).build());
            });
        }
    }

    public void deleteById(UUID id) {
       try {
           repository.deleteById(id);
       } catch (Exception e) {
           throw new CustomException(translator.getText("movieNotFoundId", id.toString()), null);
       }
    }

}
