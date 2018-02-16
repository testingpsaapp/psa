package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.psa.application.model.CommDlistConfig;
import com.psa.application.repositories.CommDlistConfigRepository;

@Service
public class CommDlistConfigService
{
	@Autowired
	CommDlistConfigRepository commDlistConfigRepository;
	
	
	public String save(CommDlistConfig commDlistConfig)
	{
		String saveResult = "{\"message\": \"Communication DList Configuration Save Failed\"}";
		CommDlistConfig  commDlistConfigSavedObj= null;
		commDlistConfigSavedObj = commDlistConfigRepository.saveAndFlush(commDlistConfig);
		System.out.println("Saved Obj"+commDlistConfigSavedObj);
		if(commDlistConfigSavedObj==null)
		{
			
		}
		else
		{
			saveResult = "{\"message\":\"Communication DList Configuration Saved Successfully\"}";
		}
		System.out.println(saveResult);
		return saveResult;
		
	}
	
	public String getCommDlistConfig(String incType, String incPriority, String commType, String impactedLob, String impactedCtry)
	{
		CommDlistConfig commDlistConfig = null;
		String result ="";
		commDlistConfig = commDlistConfigRepository.getCommDlistConfigByCTITIPIL(commType, incType, incPriority, impactedLob, impactedCtry);
		if(commDlistConfig==null)
		{
			result ="No Data";
		}
		else
		{
			result = "{toIds:"+commDlistConfig.getToIds()+","
					+ "cc:"+commDlistConfig.getCc()+","
					+ "bcc:"+commDlistConfig.getBcc()+"}";
		}
		return result;
	}
	
	public List<CommDlistConfig> getAllCommDlistConfig()
	{
		List<CommDlistConfig> listOfCommDlistConfig = null;
		listOfCommDlistConfig = commDlistConfigRepository.findAll();
		return listOfCommDlistConfig;
	}
}
