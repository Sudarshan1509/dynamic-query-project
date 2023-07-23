package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmActorId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "actor_id", nullable = false)
    @EqualsAndHashCode.Include
    private short actorId;

    @Column(name = "film_id", nullable = false)
    @EqualsAndHashCode.Include
    private short filmId;
}