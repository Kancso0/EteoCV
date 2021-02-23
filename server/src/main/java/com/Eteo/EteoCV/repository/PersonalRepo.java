package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.UserPersonal.Personal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonalRepo extends MongoRepository<Personal, String> {
    List<Personal> findAll();
    Optional<Personal> findById(String id);
}
