package com.movielist.movielist.movie.validator;

import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.movie.domain.Movie;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import com.movielist.movielist.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepeatedActorValidator implements MovieSaveValidator {

    @Autowired
    private Translator translator;

    private List<String> validationMsgs;

    @Override
    public List<String> validate(Movie movie) {
        validationMsgs = new ArrayList<>();

        Set<UUID> actors = new HashSet<>();

        movie.getCast().stream()
                .filter(movieActor -> !actors.add(movieActor.getActor().getId()))
                .map(MovieActor::getActor)
                .forEach(actor -> validationMsgs.add(translator.getText("repeatedActorCast", actor.getName())));

        return validationMsgs;
    }

}
