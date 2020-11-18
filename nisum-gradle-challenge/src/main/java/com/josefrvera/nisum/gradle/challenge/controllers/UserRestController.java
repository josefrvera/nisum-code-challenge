package com.josefrvera.nisum.gradle.challenge.controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josefrvera.nisum.gradle.challenge.models.entity.User;
import com.josefrvera.nisum.gradle.challenge.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("users")
	public ResponseEntity<?> create(@RequestBody User userRequest) {
		User userNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			userNew = userService.save(userRequest);
		
		} catch (DataAccessException e) {
			response.put("mensaje", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map <String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		} catch (Exception e) {
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map <String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		response.put("id", userNew.getId());
		response.put("created", format.format(userNew.getCreated()));
		response.put("modified", format.format(userNew.getModified()));
		response.put("last_login", format.format(userNew.getLastLogin()));
		response.put("token", userNew.getToken().toString());
		response.put("isactive", userNew.getIsActive());
		
		return new ResponseEntity<Map <String, Object>>(response, HttpStatus.CREATED);
	}

}
