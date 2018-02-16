package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psa.application.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long>{

	@Query("select e from EmailTemplate e where e.templateId=:templateId")
	public EmailTemplate getEmailTemplateByTemplateId(String templateId);
}
