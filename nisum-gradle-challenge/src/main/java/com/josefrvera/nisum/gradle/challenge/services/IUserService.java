package com.josefrvera.nisum.gradle.challenge.services;

import com.josefrvera.nisum.gradle.challenge.models.entity.User;

public interface IUserService {
	
	public User save(User user) throws Exception;
}
