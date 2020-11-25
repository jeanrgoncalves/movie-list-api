package com.movielist.movielist.movie.movieactor.domain;

import com.movielist.movielist.actor.domain.Actor;
import com.movielist.movielist.genericentitydto.BaseEntity;
import com.movielist.movielist.movie.domain.Movie;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Builder
@Getter
@Table(name = "movies_actors")
public class  MovieActor implements BaseEntity {

    @Id
    @GeneratedValue
    @Setter
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @Setter
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "id_actor")
    private Actor actor;
}
