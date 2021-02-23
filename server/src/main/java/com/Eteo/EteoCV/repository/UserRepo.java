package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findById(String id);
}
