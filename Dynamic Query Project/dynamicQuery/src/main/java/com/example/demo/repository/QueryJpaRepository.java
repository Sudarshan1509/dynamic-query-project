package com.example.demo.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueryJpaRepository {

    <T> T findOne(QueryCallback<T> callback);

    <T> List<T> findAll(QueryCallback<List<T>> callback);

    <T> Page<T> findAll(Pageable pageable, QueryCallback<Page<T>> callback);
}