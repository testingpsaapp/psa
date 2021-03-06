package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psa.application.model.EmailTemplate;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long>{

	@Query("select e from EmailTemplate e where e.templateId=:templateId")
	public EmailTemplate getEmailTemplateByTemplateId(@Param("templateId")String templateId);
}
