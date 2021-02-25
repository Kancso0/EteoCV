package com.Eteo.EteoCV.repository;

import com.Eteo.EteoCV.models.Knowledge.Knowledge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KnowledgeRepo extends MongoRepository<Knowledge, String> {

    List<Knowledge> findAll();
}
