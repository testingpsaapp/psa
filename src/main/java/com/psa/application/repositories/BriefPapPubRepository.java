package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psa.application.model.BriefPapPub;

@Repository
public interface BriefPapPubRepository extends JpaRepository<BriefPapPub,Long>{

}
