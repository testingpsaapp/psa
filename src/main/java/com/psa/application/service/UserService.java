package com.psa.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.*;

import com.psa.application.model.User;
import com.psa.application.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String registerUser(User user)
	{
		String message = "{\"message\":\"Registration Failed\"}";;
		//Step 1 Check if user already present
		User newUser = userRepository.findBySoeId(user.getSoeId());
		if(newUser!=null)
		{
			message = "{\"message\":\"User Already Exists.\"}";
		}
		//Step 2 If not present add user
		else
		{
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
			
			User registerUser = userRepository.saveAndFlush(user);
			if(registerUser!=null)
			{
				message = "{\"message\":\"Registration Successful. Please Login To Proceed.\"}";
			}
		}
		return message;
	}
}
