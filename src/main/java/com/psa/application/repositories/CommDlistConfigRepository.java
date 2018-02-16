package com.psa.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.CommDlistConfig;

@Repository
public interface CommDlistConfigRepository extends JpaRepository<CommDlistConfig, Long>{
	
	@Query("SELECT c FROM CommDlistConfig c WHERE LOWER(c.commType) = LOWER(:commType) "
			+ "and LOWER(c.incType) = LOWER(:incType)"
			+ "and LOWER(c.incPriority) = LOWER(:incPriority)"
			+ "and LOWER(c.impactedLob) = LOWER(:impactedLob)"
			+ "and LOWER(c.impactedCtry) = LOWER(:impactedCtry)")
	public CommDlistConfig getCommDlistConfigByCTITIPIL(@Param("commType") String commType, @Param("incType") String incType,
			@Param("incPriority") String incPriority,@Param("impactedLob") String impactedLob,@Param("impactedCtry") String impactedCtry);

}
