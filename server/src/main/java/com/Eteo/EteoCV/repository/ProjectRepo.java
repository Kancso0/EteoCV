package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepo extends MongoRepository<Project, String> {

    List<Project> findAll();

}
