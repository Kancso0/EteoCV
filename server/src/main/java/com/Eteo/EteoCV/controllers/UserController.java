package com.Eteo.EteoCV.controllers;

import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.User.User;
import com.Eteo.EteoCV.models.UserPersonal.Personal;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.UserRepo;
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
public class UserController {

    @Autowired
    UserRepo userRepo;


    @PostMapping("/addUser")
        public ResponseEntity<MessageResponse> addUser(@RequestBody User user) {

            userRepo.save(user);

            return new ResponseEntity<>(new MessageResponse("Successfully added User"), HttpStatus.OK);
        }

     @PostMapping("/addProjectToUser/{userId}")
     public ResponseEntity<MessageResponse> addProjectToUser(@PathVariable("userId") String userId, @RequestBody Project project) {

         Optional<User> userFromDB = userRepo.findById(userId);

         if(userFromDB.isPresent()) {
             User user = userFromDB.get();

             project.setId(new ObjectId().toString());
             user.getProjects().add(project);
             userRepo.save(user);
         } else {
             return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<>(new MessageResponse("Successfully added Project"), HttpStatus.OK);
     }

     @PostMapping("/modifyProject/{userId}")
    public ResponseEntity<?> modifyProject(@PathVariable("userId") String userId, @RequestBody Project projectFromClient) {

        Optional<User> userFromDB = userRepo.findById(userId);

         if(userFromDB.isPresent()) {
             User user = userFromDB.get();

             user.getProjects().forEach(project -> {
                 if(project.getId().equals(projectFromClient.getId())) {

                     project.setWorkplace(projectFromClient.getWorkplace());
                     project.setName(projectFromClient.getName());
                     project.setStartTime(projectFromClient.getStartTime());
                     project.setEndTime(projectFromClient.getEndTime());
                     project.setPost(projectFromClient.getPost());
                     project.setActivities(projectFromClient.getActivities());
                     project.setTools(projectFromClient.getTools());
                     project.setDescription(projectFromClient.getDescription());
                 }

             });

             userRepo.save(user);
         } else {
             return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<>(new MessageResponse("Successfully modifying Project"), HttpStatus.OK);
     }

    @GetMapping("deleteProject/{userId}/{projectID}")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable("userId") String userId, @PathVariable("projectID") String projectId) {

        Optional<User> userFromDB = userRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

           List<Project> newProjects = user.getProjects().stream().filter(project -> !project.getId().equals(projectId)).collect(Collectors.toList());
           user.setProjects(newProjects);

            userRepo.save(user);
        } else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully project deleting"), HttpStatus.OK);

    }

    @PostMapping("/modifyUserPersonal/{userId}")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable("userId") String userId, @RequestBody Personal personalFromClient) {

        Optional<User> userFromDB = userRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            user.setPersonal(personalFromClient);
            userRepo.save(user);
        }else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully personal data modifying"), HttpStatus.OK);
    }


    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {

         Optional<User> userFromDB = userRepo.findById(userId);

         if(userFromDB.isPresent()) {
             User user = userFromDB.get();
             return new ResponseEntity<>(user, HttpStatus.OK);
         } else {
             return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
         }

     }


}
