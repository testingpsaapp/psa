package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.ChangeComm;

@Repository
public interface ChangeCommRepository extends JpaRepository<ChangeComm, Long>{

}
