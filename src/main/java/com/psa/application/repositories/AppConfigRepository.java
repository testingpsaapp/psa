package com.psa.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.AppConfig;




@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {

	@Query("SELECT a FROM AppConfig a WHERE LOWER(a.lob) = LOWER(:lob) "
			+ "and LOWER(a.appName) = LOWER(:appName)")
	public AppConfig getAppConfigByLobAppName(@Param("lob") String lob, @Param("appName") String appName);
	
	@Query("SELECT a FROM AppConfig a WHERE LOWER(a.appName) = LOWER(:appName)")
	public AppConfig getAppConfigByAppName(@Param("appName") String appName);
	
	@Query("SELECT a FROM AppConfig a WHERE LOWER(a.lob) = LOWER(:lob)")
	public List<AppConfig> getAppConfigByLobName(@Param("lob") String lob);
}
