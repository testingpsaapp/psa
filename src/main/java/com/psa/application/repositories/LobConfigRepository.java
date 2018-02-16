package com.psa.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.LobConfig;

@Repository
public interface LobConfigRepository extends JpaRepository<LobConfig, Long>{
	
	@Query("SELECT l FROM LobConfig l WHERE LOWER(l.soeId) = LOWER(:soeId) ")
	public LobConfig getLobConfigBySoeId(@Param("soeId")String soeId);

}
