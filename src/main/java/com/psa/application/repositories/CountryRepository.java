package com.psa.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.Countries;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Long> {

	@Query("SELECT c FROM Countries c WHERE LOWER(c.region) = LOWER(:region)")
	public List<Countries> findAllByRegion(@Param("region") String region);

}
