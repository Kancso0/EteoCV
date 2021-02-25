package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.ProjectList.ProjectObj;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectListRepo extends MongoRepository<ProjectObj, String> {

    List<ProjectObj> findAll();
}
