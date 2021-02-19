package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.properties.Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertiesRepo extends MongoRepository<Properties, String> {

    List<Properties> findAll();

}
