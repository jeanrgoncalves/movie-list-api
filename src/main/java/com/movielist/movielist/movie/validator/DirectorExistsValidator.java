package com.movielist.movielist.movie.validator;

import com.movielist.movielist.director.domain.DirectorRepository;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DirectorExistsValidator implements MovieSaveValidator {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private Translator translator;

    @Override
    public List<String> validate(Movie movie) {
        List<String> validationMsgs = new ArrayList<>();

        if ( movie.getDirector() != null) {
            UUID directorId = movie.getDirector().getId();
            if (!directorRepository.findById(directorId).isPresent())
                validationMsgs.add(translator.getText("directorNotFoundId", directorId.toString()));
        }


        return validationMsgs;
    }

}
