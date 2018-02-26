package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.ChangeComm;

@Repository
public interface ChangeCommRepository extends JpaRepository<ChangeComm, Long>{

	@Query("Select c from ChangeComm c where LOWER(c.changNum) = LOWER(:changeNum)")
	public ChangeComm findChangeCommByChangeNum(@Param("changeNum")String changeNum);

}
