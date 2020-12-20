package com.happyWatter.codivAlarm.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UserExtendsRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;




}
