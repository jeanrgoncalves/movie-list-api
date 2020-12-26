package com.movielist.movielist.movie.validator;

import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.domain.MovieRepository;
import com.movielist.movielist.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDirectorAlreadyExistsValidator implements MovieSaveValidator {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private Translator translator;

    private List<String> validationMsgs;

    @Override
    public List<String> validate(Movie movie) {
        validationMsgs = new ArrayList<>();

        if ( movie.getDirector() != null) {
            List<Movie> foundMovie = repository.findByNameAndDirectorId(movie.getName(), movie.getDirector().getId());
            if (!foundMovie.isEmpty()) {
                if (movie.getId() == null) {
                    addValidationMsg(foundMovie.get(0));
                } else {
                    if (movie.getId().compareTo(foundMovie.get(0).getId()) != 0) {
                        addValidationMsg(foundMovie.get(0));
                    }
                }
            }
        }

        return validationMsgs;
    }

    private void addValidationMsg(Movie foundMovie) {
        String msg = translator.getText("movieDirectorAlreadyExists", foundMovie.getName(), foundMovie.getDirector().getName());
        validationMsgs.add(msg);
    }
}
