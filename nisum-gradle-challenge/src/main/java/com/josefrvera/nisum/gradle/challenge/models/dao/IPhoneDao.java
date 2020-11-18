package com.josefrvera.nisum.gradle.challenge.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.josefrvera.nisum.gradle.challenge.models.entity.Phone;

public interface IPhoneDao extends CrudRepository<Phone, Long>{

}
