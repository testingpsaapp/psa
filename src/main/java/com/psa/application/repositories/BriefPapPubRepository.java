package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.BriefPapPub;

@Repository
public interface BriefPapPubRepository extends JpaRepository<BriefPapPub,Long>{

	@Query("SELECT b from BriefPapPub b where b.mimNum = :mimNum")
	BriefPapPub findBriefPapPubByMimNum(@Param("mimNum") String mimNum);
}
