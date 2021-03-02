package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.Knowledge.Knowledge;
import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.User.User;
import com.Eteo.EteoCV.models.UserPersonal.Personal;
import com.Eteo.EteoCV.payloads.request.Dto.KnowledgeDto;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.UserCvRepo;
import com.Eteo.EteoCV.services.UserCvService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserCvController {

    @Autowired
    private UserCvRepo userCvRepo;

    @Autowired
    private UserCvService userCvService;



    @PostMapping("/add")
        public ResponseEntity<MessageResponse> addUser(@RequestBody User user) {

            userCvRepo.save(user);

            return new ResponseEntity<>(new MessageResponse("Successfully added User"), HttpStatus.OK);
        }

     @PostMapping("/{userId}/project/add")
     public ResponseEntity<MessageResponse> addProjectToUser(@PathVariable("userId") String userId, @RequestBody Project project) {

       return  userCvService._addProjectToUser(userId, project);

     }

     @PostMapping("/{userId}/project/modify")
    public ResponseEntity<MessageResponse> modifyProject(@PathVariable("userId") String userId, @RequestBody Project projectFromClient) {

         return  userCvService._modifyProject(userId, projectFromClient);

     }

    @GetMapping("/{userId}/project/{projectID}/remove")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable("userId") String userId, @PathVariable("projectID") String projectId) {

        return userCvService._deleteProject(userId, projectId);

    }

    @PostMapping("/{userId}/personal/modify")
    public ResponseEntity<MessageResponse> modifyPersonalData(@PathVariable("userId") String userId, @RequestBody Personal personalFromClient) {

       return userCvService._modifyPersonalData(userId, personalFromClient);

    }


    @GetMapping("/{userId}/get")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {

         return userCvService._getUser(userId);

     }

    @PostMapping("/{userId}/knowledge")
    public ResponseEntity<MessageResponse> addKnowledgeToUser(@PathVariable("userId") String userId, @RequestBody KnowledgeDto knowledgeDto) {

        return userCvService._modifyUserKnowlegde(userId, knowledgeDto);
    }

}
