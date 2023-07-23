package com.example.demo.service;

import com.example.demo.dto.FilmDto;
import com.example.demo.repository.FilmDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QueryService {

    private final FilmDao filmDao;

    public List<FilmDto> retrieveFilms(FilmSearchCriteria searchCriteria) {

        return this.filmDao.findFilms(searchCriteria);
    }
}