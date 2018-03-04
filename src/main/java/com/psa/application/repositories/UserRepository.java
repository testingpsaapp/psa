package com.psa.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psa.application.model.User;

public interface UserRepository extends JpaRepository<User,Long> 
{

}
