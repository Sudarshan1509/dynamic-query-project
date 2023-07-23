package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilmSearchCriteria {

    private BigDecimal minRentalRate;
    private BigDecimal maxRentalRate;
    private Long releaseYear;
    private Set<String> categories;
}