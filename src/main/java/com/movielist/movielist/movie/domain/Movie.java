package com.movielist.movielist.movie.domain;

import com.movielist.movielist.director.domain.Director;
import com.movielist.movielist.genericentitydto.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
@Builder
@Getter
@Setter
@Table(name = "movies")
public class Movie implements BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_director")
    private Director director;

    @Column(name = "already_seen")
    private boolean alreadySeen = false;

}
