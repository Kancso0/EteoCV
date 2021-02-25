package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.PersonalProperties.PersonalProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalPropertiesRepo extends MongoRepository<PersonalProperties, String> {

    List<PersonalProperties> findAll();

}
