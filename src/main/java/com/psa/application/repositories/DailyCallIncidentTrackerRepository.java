package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.psa.application.model.DailyCallIncidentTracker;

@Repository
public interface DailyCallIncidentTrackerRepository extends JpaRepository<DailyCallIncidentTracker, Long> {
	
	@Query("Select d from DailyCallIncidentTracker d where LOWER(d.incNum)=LOWER(:incNum)")
	public DailyCallIncidentTracker getDailyCallIncidentTrackerByIncNum(String incNum);

}
