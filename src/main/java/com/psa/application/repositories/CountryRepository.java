package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.Countries;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {

}
