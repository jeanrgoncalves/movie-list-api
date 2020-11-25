package com.movielist.movielist.movie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.genericentitydto.BaseEntity;
import com.movielist.movielist.movie.movieactor.domain.MovieActor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Builder
@Getter
@Table(name = "movies")
public class Movie implements BaseEntity {

    @Id
    @Setter
    private UUID id;

    @NotBlank
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_director")
    @Setter
    private Director director;

    @Column(name = "already_seen")
    @Setter
    private boolean alreadySeen = false;

    @Builder.Default
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieActor> cast = new ArrayList<>();

    public void addActor(MovieActor movieActor) {
        cast.add(movieActor);
    }
}
