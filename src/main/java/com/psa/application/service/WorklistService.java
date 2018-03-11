package com.psa.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.Worklist;
import com.psa.application.repositories.WorklistRepository;

@Service
public class WorklistService {
	
	@Autowired
	WorklistRepository worklistRepository;

	public List<Worklist> getUserWorklist(String user) {
		// TODO Auto-generated method stub
		return worklistRepository.findWorklistByTaskOwner(user);
	}

}
