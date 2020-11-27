package com.movielist.movielist.actor.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    ActorRepository repository;

    public Actor save(Actor actor) {
        return repository.save(actor);
    }

}
