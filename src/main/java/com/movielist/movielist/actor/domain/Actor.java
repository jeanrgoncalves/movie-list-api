package com.movielist.movielist.actor.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
@Builder
@Getter
@Setter
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    LocalDate birthDate;

}
