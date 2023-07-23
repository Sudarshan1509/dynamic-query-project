package com.example.demo.controller;

import com.example.demo.dto.FilmDto;
import com.example.demo.feign.AuthClient;
import com.example.demo.service.QueryService;
import com.example.demo.service.FilmSearchCriteria;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/films", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilmController {

    private final QueryService dvdRentalService;

    @GetMapping(path = "")
    public ResponseEntity<List<FilmDto>> retrieveFilms(

            @RequestParam(required = false)
            Optional<BigDecimal> minRentalRate,

            @RequestParam(required = false)
            Optional<BigDecimal> maxRentalRate,

            @RequestParam(required = false)
            Optional<Long> releaseYear,

            @RequestParam(name="category", required = false)
            Set<String> categories) {

        FilmSearchCriteria searchCriteria = FilmSearchCriteria.builder()
                .minRentalRate(minRentalRate.orElseGet(() -> null))
                .maxRentalRate(maxRentalRate.orElseGet(() -> null))
                .releaseYear(releaseYear.orElseGet(() -> null))
                .categories(categories)
                .build();

        List<FilmDto> films = dvdRentalService.retrieveFilms(searchCriteria);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
}