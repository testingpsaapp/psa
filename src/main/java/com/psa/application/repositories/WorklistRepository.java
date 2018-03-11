package com.psa.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.Worklist;

@Repository
public interface WorklistRepository extends JpaRepository<Worklist,Long> {

	@Query("SELECT w from Worklist w where LOWER(w.taskId) =LOWER (:changeNum) "
			+ "and LOWER(w.taskOwner) = LOWER (:taskOwner) "
			+ "and w.status = 'ACTIVE'")
	List<Worklist> findWorklistByTaskIdTaskOwnerAppName(@Param("changeNum")String changeNum,@Param("taskOwner") String taskOwner);

	@Query("SELECT w from Worklist w where LOWER(w.taskId) = (:changeNum) and w.status = 'ACTIVE'")
	List<Worklist> findWorklistByTaskIdStatus(@Param("changeNum")String changeNum);

	@Query("SELECT w from Worklist w where LOWER(w.taskId) = (:taskid) and w.status = 'ACTIVE'")
	List<Worklist> getBriefingPaperTaskForReviewByLOBLead(@Param("taskid")String taskid);
	
	@Query("SELECT w from Worklist w where w.status = 'ACTIVE'")
	List<Worklist> findAllActive();

	@Query("SELECT w from Worklist w where w.status = 'ACTIVE' and lower(w.taskOwner) = lower(:user)")
	List<Worklist> findWorklistByTaskOwner(@Param("user")String user);

	@Query("SELECT count(*) from Worklist w where w.status = 'ACTIVE' and lower(w.taskOwner) = lower(:user)")
	int getUserWorklistCount(@Param("user")String user);

}
