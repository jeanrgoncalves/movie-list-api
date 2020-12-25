package com.movielist.movielist.actor.domain;

import com.movielist.movielist.genericentitydto.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor()
@Builder
@Getter
@Setter
@Table(name = "actors")
public class Actor implements BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

}
