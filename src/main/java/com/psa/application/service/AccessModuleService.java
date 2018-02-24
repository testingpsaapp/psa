package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.AccessModule;
import com.psa.application.repositories.AccessModuleRepository;

@Service
public class AccessModuleService 
{
	@Autowired
	AccessModuleRepository accessModuleRepository;
	public List<AccessModule> getAllAccessModule()
	{
		return accessModuleRepository.findAll();
	}
	
	public List<AccessModule> getAllAccessModuleByMainModule(String mainModule)
	{
		return accessModuleRepository.findAllAccessModuleByMainModule(mainModule);
	}
	
	public List<AccessModule> getAllAccessModuleByMainModuleAndSubModule(String mainModule, String subModule)
	{
		return accessModuleRepository.findAllAccessModuleByMainModuleAndSubModule(mainModule,subModule);
	}
}
