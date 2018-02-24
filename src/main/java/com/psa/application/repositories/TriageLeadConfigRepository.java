package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.TriageLeadConfig;

@Repository
public interface TriageLeadConfigRepository extends JpaRepository<TriageLeadConfig, Long>{

}
