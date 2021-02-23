package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.ProjectList.ProjectObj;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.ProjectListRepo;
import com.Eteo.EteoCV.repository.PersonalPropertiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    ProjectListRepo projectListRepo;

    @Autowired
    PersonalPropertiesRepo personalPropertiesRepo;


    @PostMapping("/addToProjectList")
        public ResponseEntity<MessageResponse> addToProjectList(@RequestBody ProjectObj projectObj) {

        projectListRepo.save(projectObj);

        return new ResponseEntity<>(new MessageResponse("Successfully added to ProjectList"), HttpStatus.OK);
    }

    @GetMapping("/getAllProjectFromList")
    public ResponseEntity<List<Project>> getAllProjectFromList() {

        List<ProjectObj> projectList = projectListRepo.findAll();

        return new ResponseEntity(projectList, HttpStatus.OK);
    }

}
