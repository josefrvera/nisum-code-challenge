package com.josefrvera.nisum.gradle.challenge.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.josefrvera.nisum.gradle.challenge.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{
	
	public List<User> findByEmail(String email);
}
