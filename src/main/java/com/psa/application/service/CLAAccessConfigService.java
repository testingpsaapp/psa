package com.psa.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.CLAAcessConfig;
import com.psa.application.repositories.CLAAcessConfigRepository;

@Service
public class CLAAccessConfigService {

	@Autowired
	CLAAcessConfigRepository cLAAcessConfigRepository;
	
	public String saveCLAAcessConfig(CLAAcessConfig cLAAcessConfig)
	{
		String result ="{\"message\": \"PSA Access Configuration Save Failed\"}";
		CLAAcessConfig newCLAAcessConfig =null;
		newCLAAcessConfig=cLAAcessConfigRepository.saveAndFlush(cLAAcessConfig);
		if(newCLAAcessConfig==null)
		{
			
		}
		else
		{
			result ="{\"message\": \"PSA Access Configuration Saved Successfully\"}";
		}
		
		return result;
	}
	
	public CLAAcessConfig getCLAAcessConfigBySoeId(String soeId)
	{
		CLAAcessConfig cLAAcessConfig = null;
		cLAAcessConfig = cLAAcessConfigRepository.getCLAAcessConfigBySoeId(soeId);
		return cLAAcessConfig;
	}
}
