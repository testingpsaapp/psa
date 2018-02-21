package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.IncidentComm;

@Repository
public interface IncidentCommRepository extends JpaRepository<IncidentComm,Long>{

	@Query("SELECT i from IncidentComm i where LOWER(incidentNum)=LOWER(:incNum)")
	IncidentComm findIncCommByIncNum(@Param("incNum") String incNum);

}
