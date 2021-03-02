package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.PersonalProperties.PersonalProperties;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.PersonalPropertiesRepo;
import com.Eteo.EteoCV.repository.PersonalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    PersonalRepo personalRepo;

    @Autowired
    PersonalPropertiesRepo personalPropertiesRepo;

    @PostMapping("/properties/add")
    public ResponseEntity<MessageResponse> addPersonalProperties(@RequestBody PersonalProperties personalProperties) {

        personalPropertiesRepo.save((personalProperties));

        return new ResponseEntity<>(new MessageResponse("Successfully added  Personal Properties List"), HttpStatus.OK);
    }

    @GetMapping("/properties/get")
    public ResponseEntity<List<PersonalProperties>> getPersonalProperties() {

        List<PersonalProperties> properties =  personalPropertiesRepo.findAll();

        return new ResponseEntity<>(properties, HttpStatus.OK);
    }



}
