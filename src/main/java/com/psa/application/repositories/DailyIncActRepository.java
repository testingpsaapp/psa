package com.psa.application.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.DailyIncAct;

@Repository
public interface DailyIncActRepository extends JpaRepository<DailyIncAct,Long> {
	
	@Query("Select d from DailyIncAct d where tlSoeid =:tlSoeid")
	public List<DailyIncAct> findByTLSoeid(@Param("tlSoeid") String tlSoeid);
	
	@Query("Select d from DailyIncAct d where date =:date and tlSoeid =:tlSoeid")
	public List<DailyIncAct> findByDate(@Param("date") Date date,@Param("tlSoeid") String tlSoeid);
	
	@Query("Select d from DailyIncAct d where incNum =:incNum")
	public DailyIncAct findByIncNum(@Param("incNum") String incNum);

}
