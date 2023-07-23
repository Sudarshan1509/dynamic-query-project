package com.example.demo.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmDto {

    @EqualsAndHashCode.Include
    private int filmId;

    private String lang;
    private String title;
    private String description;
    private BigDecimal rentalRate;
    private String releaseYear;
}