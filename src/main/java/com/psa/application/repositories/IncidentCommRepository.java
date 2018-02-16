package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.IncidentComm;

@Repository
public interface IncidentCommRepository extends JpaRepository<IncidentComm,Long>{

}
