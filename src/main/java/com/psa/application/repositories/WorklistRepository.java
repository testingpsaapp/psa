package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.Worklist;

@Repository
public interface WorklistRepository extends JpaRepository<Worklist,Long> {

}
