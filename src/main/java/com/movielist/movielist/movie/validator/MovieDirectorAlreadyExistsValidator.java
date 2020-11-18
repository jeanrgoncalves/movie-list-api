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
    MovieRepository repository;

    @Autowired
    Translator translator;

    @Override
    public List<String> validate(Movie movie) {
        List<String> validationMsgs = new ArrayList<>();

        List<Movie> foundMovie = repository.findByNameAndDirectorId(movie.getName(), movie.getDirector().getId());
       if (!foundMovie.isEmpty()) {
            String msg = translator.getText("movieDirectorAlreadyExists", foundMovie.get(0).getName(), foundMovie.get(0).getDirector().getName());
            validationMsgs.add(msg);
       }

       return validationMsgs;
    }
}
