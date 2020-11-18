package com.movielist.movielist.director.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
@Builder
@Getter
@Setter
@Table(name = "directors")
public class Director {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}
