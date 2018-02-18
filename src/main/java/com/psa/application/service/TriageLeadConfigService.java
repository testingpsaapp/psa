package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.TriageLeadConfig;
import com.psa.application.repositories.TriageLeadConfigRepository;

@Service
public class TriageLeadConfigService 
{
	@Autowired
	TriageLeadConfigRepository triageLeadConfigRepository;
	public String saveTriageLeadConfiguration(TriageLeadConfig triageLeadConfig)
	{
		String message="{\"message\":\"Triage Lead Configuration Save Failed\"}";
		TriageLeadConfig newTriageLeadConfig=null;
		newTriageLeadConfig=triageLeadConfigRepository.saveAndFlush(triageLeadConfig);
		if(newTriageLeadConfig==null)
		{
			
		}
		else
		{
			message="{\"message\":\"Triage Lead Configuration Saved Successfully\"}";
		}
		return message;
	}
	public List<TriageLeadConfig> getAllTriageLeadConfiguration() {
		List<TriageLeadConfig> listOfTriageLeadConfig = null;
		listOfTriageLeadConfig=triageLeadConfigRepository.findAll();
		return listOfTriageLeadConfig;
	}
}
