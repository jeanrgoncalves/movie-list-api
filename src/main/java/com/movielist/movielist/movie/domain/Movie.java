package com.movielist.movielist.movie.domain;

import com.movielist.movielist.genericentitydto.BaseEntity;
import lombok.*;

import javax.persistence.*;
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

    private String name;

    @Column(name = "already_seen")
    private boolean alreadySeen = false;

}
