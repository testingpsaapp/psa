package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.Countries;
import com.psa.application.repositories.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepository;
	
	public List<Countries> getAllCountry()
	{
		List<Countries> allCountry = countryRepository.findAll();
		return allCountry;
	}
	
	public List<Countries> saveCountry(Countries country)
	{
		countryRepository.saveAndFlush(country);		
		List<Countries> allCountry = countryRepository.findAll();
		return allCountry;
	}


}
