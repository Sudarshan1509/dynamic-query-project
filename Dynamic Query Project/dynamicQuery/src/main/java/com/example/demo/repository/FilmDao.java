package com.example.demo.repository;

import com.example.demo.domain.Film;
import com.example.demo.dto.FilmDto;
import com.example.demo.service.FilmSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.ObjectUtils;
import com.google.common.collect.Maps;

@Repository
public interface FilmDao extends JpaRepository<Film, Integer>, QueryJpaRepository {


    default List<FilmDto> findFilms(FilmSearchCriteria criteria) {

    return this.findAll(new QueryCallback<List<FilmDto>>() {
          @Override
          public List doWithEntityManager(EntityManager entityManager) {
            Map<String, Object> queryParams = this.buildQueryParameters();

            StringBuilder builder = new StringBuilder();
            // SELECT
            builder.append("SELECT " + System.lineSeparator());
            builder.append(
                "  f.film_id as f_film_id, f.title, f.description, " + System.lineSeparator());
            builder.append(
                "  f.release_year, f.rental_rate, " + System.lineSeparator());

            builder.append("  l.name as l_name " + System.lineSeparator());

            // FROM
            builder.append("FROM dynamicQuery.film f " + System.lineSeparator());
            if (queryParams.get("categories") != null) {
              builder.append(
                  "  INNER JOIN dynamicQuery.film_category fc ON f.film_id = fc.film_id "
                      + System.lineSeparator());
              builder.append(
                  "  INNER JOIN dynamicQuery.category c ON fc.category_id = c.category_id "
                      + System.lineSeparator());
            }
            builder.append(
                "  LEFT OUTER JOIN dynamicQuery.language l ON f.language_id = l.language_id "
                    + System.lineSeparator());

            // WHERE
            builder.append("WHERE 1 = 1 " + System.lineSeparator());
            if (queryParams.get("minRate") != null) {
              builder.append(
                  "  AND f.rental_rate between :minRate AND :maxRate " + System.lineSeparator());
            }
            if (queryParams.get("relYear") != null) {
              builder.append("  AND f.release_year = :relYear " + System.lineSeparator());
            }
            if (CollectionUtils.isNotEmpty(criteria.getCategories())) {
              builder.append("  AND c.name IN (:categories) " + System.lineSeparator());
            }

            // Create query
            String nativeQuery = builder.toString();
            Query query = entityManager.createNativeQuery(nativeQuery, "FilmDtoMapping");

            // Set query parameters
            if (queryParams.get("categories") != null) {
              query.setParameter("categories", queryParams.get("categories"));
            }
            if (queryParams.get("minRate") != null) {
              query.setParameter("minRate", queryParams.get("minRate"));
              query.setParameter("maxRate", queryParams.get("maxRate"));
            }
            if (queryParams.get("relYear") != null) {
              query.setParameter("relYear", queryParams.get("relYear"));
            }

            return query.getResultList();
          }

          private Map<String, Object> buildQueryParameters() {
            Map<String, Object> result = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(criteria.getCategories())) {
              result.put("categories", criteria.getCategories());
            }
            if (ObjectUtils.allNotNull(criteria.getMinRentalRate(), criteria.getMaxRentalRate())) {
              result.put("minRate", criteria.getMinRentalRate());
              result.put("maxRate", criteria.getMaxRentalRate());
            }
            if (criteria.getReleaseYear() != null) {
              result.put("relYear", criteria.getReleaseYear());
            }
            return result;
          }
        });
    }
}