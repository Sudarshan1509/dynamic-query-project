package com.example.demo.repository;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface QueryCallback<T> {

    T doWithEntityManager(EntityManager entityManager);
}