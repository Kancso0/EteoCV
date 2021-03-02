package com.Eteo.EteoCV.services;

import com.Eteo.EteoCV.models.Knowledge.Knowledge;
import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.User.User;
import com.Eteo.EteoCV.models.UserPersonal.Personal;
import com.Eteo.EteoCV.payloads.request.Dto.KnowledgeDto;
import com.Eteo.EteoCV.payloads.response.MessageResponse;
import com.Eteo.EteoCV.repository.UserCvRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCvService {

    @Autowired
    private UserCvRepo userCvRepo;

    Logger logger = LoggerFactory.getLogger(UserCvService.class);

    public ResponseEntity<MessageResponse> _addProjectToUser(String userId, Project project) {
        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            Project newProject = new Project(project);

           // project.setId(new ObjectId().toString());
            user.getProjects().add(newProject);
            userCvRepo.save(user);
        } else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MessageResponse("Successfully added Project"), HttpStatus.OK);
    }


    public ResponseEntity<MessageResponse> _modifyProject(String userId, Project projectFromClient) {

        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            List<Project> newProjects = user.getProjects().stream().map(project -> {
                if(project.getId().equals(projectFromClient.getId())) {
                    return project.rebuildProject(projectFromClient);
                }
                return project;
            }).collect(Collectors.toList());

            User newUser = new User(user.getId(), user.getPersonal(), user.getKnowledge(), newProjects);

         /*  for(Project project : user.getProjects()) {
                if(project.getId().equals(projectFromClient.getId())) {

                   /*Project newP = project.rebuildProject(projectFromClient);

                    project.setWorkplace(projectFromClient.getWorkplace());
                    project.setName(projectFromClient.getName());
                    project.setStartTime(projectFromClient.getStartTime());
                    project.setEndTime(projectFromClient.getEndTime());
                    project.setPost(projectFromClient.getPost());
                    project.setActivities(projectFromClient.getActivities());
                    project.setTools(projectFromClient.getTools());
                    project.setDescription(projectFromClient.getDescription());

                }
            }*/
            userCvRepo.save(newUser);

        } else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully modifying Project"), HttpStatus.OK);
    }


    public ResponseEntity<MessageResponse> _deleteProject(String userId, String projectId) {

        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            List<Project> newProjectList = user.getProjects().stream().filter(project -> !project.getId().equals(projectId)).collect(Collectors.toList());

            User newUser = new User(user.getId(), user.getPersonal(), user.getKnowledge(), newProjectList);
          //  user.setProjects(newProjects);

            userCvRepo.save(newUser);
        } else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully project deleting"), HttpStatus.OK);
    }

    public ResponseEntity<MessageResponse> _modifyPersonalData(String userId, Personal personalFromClient) {

        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            User newUser = new User(user.getId(), personalFromClient, user.getKnowledge(), user.getProjects());

           // user.setPersonal(personalFromClient);
            userCvRepo.save(newUser);
        }else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully personal data modifying"), HttpStatus.OK);
    }

    public ResponseEntity<?> _getUser(String userId) {

        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<MessageResponse> _modifyUserKnowlegde(String userId, KnowledgeDto knowledgeDto) {

        Optional<User> userFromDB = userCvRepo.findById(userId);

        if(userFromDB.isPresent()) {
            User user = userFromDB.get();

            Knowledge knowledge = new Knowledge(knowledgeDto);

            User newUser = new User(user.getId(), user.getPersonal(), knowledge, user.getProjects());

            userCvRepo.save(newUser);
        }else {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MessageResponse("Successfully knowledge data modifying"), HttpStatus.OK);
    }
}
