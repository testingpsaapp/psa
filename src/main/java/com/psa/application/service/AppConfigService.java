package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.AppConfig;
import com.psa.application.repositories.AppConfigRepository;

@Service
public class AppConfigService {

	@Autowired
	AppConfigRepository appConfigRepository;
	
	public String saveAppConfig(AppConfig appConfig)
	{
		String result ="App Config Save Failed";
		AppConfig savedAppConfig = null;
		savedAppConfig = appConfigRepository.saveAndFlush(appConfig);
		if(savedAppConfig == null)
		{
			
		}
		else
		{
			result ="App Config Save Sucessful";
		}
		return result;
	}
	
	public String getAppConfigByLobAppName(String lob,String appName)
	{
		String result ="No Data";
		AppConfig newAppConfig = null;
		newAppConfig = appConfigRepository.getAppConfigByLobAppName(lob, appName);
		if(newAppConfig==null)
		{
			
		}
		else
		{
			result ="{dList:"+newAppConfig.getdList()+","
					+"snowDlist:"+newAppConfig.getSnowDlist()+","
					+"lobLead:"+newAppConfig.getLobLead()+","
					+"psm:"+newAppConfig.getPsm()+","
					+"pssm:"+newAppConfig.getPssm()
					+"}";
		}
		return result;
	}
	
	public List<AppConfig> getAppConfigByLobName(String lob)
	{
		List<AppConfig> listOfAppConfig=null;
		listOfAppConfig = appConfigRepository.getAppConfigByLobName(lob);
		return listOfAppConfig;
	}
}
