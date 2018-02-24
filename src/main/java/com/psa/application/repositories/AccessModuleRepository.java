package com.psa.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.AccessModule;

@Repository
public interface AccessModuleRepository extends JpaRepository<AccessModule, Long>{

	@Query("SELECT a from AccessModule a where LOWER(mainModule) = LOWER(:mainModule)")
	List<AccessModule> findAllAccessModuleByMainModule(@Param("mainModule")String mainModule);

	@Query("SELECT a from AccessModule a where LOWER(mainModule) = LOWER(:mainModule) AND LOWER(subModule) = LOWER(:subModule)")
	List<AccessModule> findAllAccessModuleByMainModuleAndSubModule(@Param("mainModule")String mainModule, @Param("subModule")String subModule);

}
