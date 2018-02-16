package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.CLAAcessConfig;

@Repository
public interface CLAAcessConfigRepository extends JpaRepository<CLAAcessConfig,Long>{

	@Query("SELECT l FROM CLAAcessConfig l WHERE LOWER(l.soeId) = LOWER(:soeId)")
	public CLAAcessConfig getCLAAcessConfigBySoeId(@Param("soeId") String soeId);
}
