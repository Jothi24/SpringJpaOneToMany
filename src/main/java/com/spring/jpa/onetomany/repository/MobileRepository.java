package com.spring.jpa.onetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.onetomany.model.Mobile;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
  
  List<Mobile> findByMobileCompanyContaining(String title);
}
