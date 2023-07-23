package com.example.demo.domain;

import com.example.demo.dto.FilmDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film", schema = "dynamicQuery")
@Getter
@Setter
@SqlResultSetMapping(
    name = "FilmDtoMapping",
    classes = {
      @ConstructorResult(
          targetClass = FilmDto.class,
          columns = {
            @ColumnResult(name = "f_film_id"),
            @ColumnResult(name = "l_name"),
            @ColumnResult(name = "title"),
            @ColumnResult(name = "description", type = String.class),
            @ColumnResult(name = "rental_rate"),
            @ColumnResult(name = "release_year", type = String.class)
          })
    })
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "film_id", unique = true, nullable = false)
    private int filmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    @Lob
    private String releaseYear;

    @Column(name = "rental_rate", nullable = false, precision = 4)
    private BigDecimal rentalRate;

    @Column(name = "rating")
    private String rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
    private Set<FilmCategory> filmCategories = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
    private Set<FilmActor> filmActors = new HashSet<>(0);
}