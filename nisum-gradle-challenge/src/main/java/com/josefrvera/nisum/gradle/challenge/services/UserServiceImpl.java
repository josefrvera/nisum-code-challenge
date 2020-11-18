package com.josefrvera.nisum.gradle.challenge.services;

import java.util.Date;
import java.util.UUID;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josefrvera.nisum.gradle.challenge.models.dao.IPhoneDao;
import com.josefrvera.nisum.gradle.challenge.models.dao.IUserDao;
import com.josefrvera.nisum.gradle.challenge.models.entity.Phone;
import com.josefrvera.nisum.gradle.challenge.models.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IPhoneDao phoneDao;
	
	@Override
	public User save(User user) throws Exception {
		User userNew;
		if (!userDao.findByEmail(user.getEmail()).isEmpty())
			throw new Exception("El correo ya registrado");
		
		String regexPass = "[A-Z][a-z]*[0-9]{2}";
		if (!user.getPassword().matches(regexPass))
			throw new Exception("La clave debe componerse de una mayúscula, letras minúsculas, y dos números");
		
		String regexEmail = "^(.+)@(.+)$";
		if (!user.getEmail().matches(regexEmail))
			throw new Exception("La dirección de correo no es válida");
		
		Date date = new Date();
		user.setCreated(date); 
		user.setModified(date);
		user.setLastLogin(date);
		user.setToken(UUID.randomUUID());
		user.setIsActive("YES");
		
		userNew = userDao.save(user);
		
		for (Phone phone : user.getPhones()) {
			phone.setUser(userNew);
			phone = phoneDao.save(phone);
		}
		
		return userNew;
		
	}

}
