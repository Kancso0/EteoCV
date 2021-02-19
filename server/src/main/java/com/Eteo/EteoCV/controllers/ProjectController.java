package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.ProjectList.ProjectObj;
import com.Eteo.EteoCV.models.properties.Properties;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.ProjectListRepo;
import com.Eteo.EteoCV.repository.ProjectRepo;
import com.Eteo.EteoCV.repository.PropertiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    ProjectListRepo projectListRepo;

    @Autowired
    PropertiesRepo propertiesRepo;

    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestBody Project project) {

        projectRepo.save(project);

        return new ResponseEntity<>(new MessageResponse("Successfully added to Project"), HttpStatus.OK);
    }


    @GetMapping("/getProjects")
    public ResponseEntity<List<Project>> getProjects() {

        List<Project> allProject = projectRepo.findAll();

        return new ResponseEntity(allProject, HttpStatus.OK);
    }

    @PostMapping("/addToProjectList")
        public ResponseEntity<?> addToProjectList(@RequestBody ProjectObj projectObj) {

        projectListRepo.save(projectObj);

        return new ResponseEntity<>(new MessageResponse("Successfully added to ProjectList"), HttpStatus.OK);
    }

    @GetMapping("/getAllProjectFromList")
    public ResponseEntity<List<Project>> getAllProjectFromList() {

        List<ProjectObj> projectList = projectListRepo.findAll();

        return new ResponseEntity(projectList, HttpStatus.OK);
    }

    @PostMapping("/addProperties")
    public ResponseEntity<?> addProperties(@RequestBody Properties properties) {

        propertiesRepo.save(properties);

        return new ResponseEntity<>(new MessageResponse("Successfully added to Properties"), HttpStatus.OK);
    }

    @GetMapping("/getProperties")
    public ResponseEntity<List<Properties>> getAllProperties() {

        List<Properties> properties = propertiesRepo.findAll();

        return new ResponseEntity(properties, HttpStatus.OK);
    }
}
