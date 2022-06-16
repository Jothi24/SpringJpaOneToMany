package com.spring.jpa.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.onetomany.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
}
