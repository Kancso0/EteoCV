package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.Knowledge.Knowledge;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.KnowledgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private KnowledgeRepo knowledgeRepo;

    @PostMapping("/list/add")
    public ResponseEntity<MessageResponse> addKnowledgeList(@RequestBody Knowledge knowledge) {

        knowledgeRepo.save(knowledge);

        return new ResponseEntity<>(new MessageResponse("Successfully added  Knowledge List"), HttpStatus.OK);
    }

    @GetMapping("/list/get")
    public ResponseEntity<List<Knowledge>> getKnowledgeList() {

        return new ResponseEntity<>(knowledgeRepo.findAll(), HttpStatus.OK);
    }
}
