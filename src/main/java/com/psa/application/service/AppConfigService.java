package com.psa.application.service;

import java.util.ArrayList;
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
		String result ="{\"message\":\"Application Configuration Save Failed\"}";
		AppConfig savedAppConfig = null;
		savedAppConfig = appConfigRepository.saveAndFlush(appConfig);
		if(savedAppConfig == null)
		{
			
		}
		else
		{
			result ="{\"message\":\"Application Configuration Saved Successfully\"}";
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
	
	public List<AppConfig> getAllAppConfig()
	{
		List<AppConfig> listOfAppConfig=null;
		listOfAppConfig = appConfigRepository.findAll();
		return listOfAppConfig;
	}

	public List<AppConfig> getAppConfigByMultipleLobName(String[] lob) 
	{
		List<AppConfig> listOfAppConfig=new ArrayList<AppConfig> ();
		//String temporaryLob="";
		System.out.println("Lobs received::"+lob);
		
			for(int i=0;i<lob.length;i++)
			{
				System.out.println("Lob::"+lob[i]);
				List<AppConfig> tempList=appConfigRepository.getAppConfigByLobName(lob[i]);
				for(int j=0;j<tempList.size();j++)
				{
					System.out.println(tempList.get(j).getAppName());
					listOfAppConfig.add(tempList.get(j));
				}
			}
		
		 
		
		return listOfAppConfig;
	}
}
