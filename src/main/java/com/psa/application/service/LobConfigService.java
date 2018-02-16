package com.psa.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.LobConfig;
import com.psa.application.repositories.LobConfigRepository;

@Service
public class LobConfigService 
{
	@Autowired
	LobConfigRepository lobConfigRepository;
	
	public String saveLobConfig(LobConfig lobConfig)
	{
		String result ="Lob Config Save Failed";
		LobConfig lobConfigNew = null;
		lobConfigNew=lobConfigRepository.saveAndFlush(lobConfig);
		if(lobConfigNew==null)
		{
			
		}
		else
		{
			result = "Lob Config Save Successful";
		}
		
		return result;
	}
	
	public LobConfig getLobConfigBySoeId(String soeId)
	{
		LobConfig lobConfig = null;
		lobConfig = lobConfigRepository.getLobConfigBySoeId(soeId);
		return lobConfig;
	}
}
