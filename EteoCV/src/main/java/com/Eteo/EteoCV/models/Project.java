package com.Eteo.EteoCV.models;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter


@AllArgsConstructor

public class Project {

    @Id
    private String id;
    private String workplace;
    private String startTime;
    private String endTime;
    private String name;
    private String post;
    private String description;
    private List<String> activities = new ArrayList<>();
    private List<String> tools = new ArrayList<>();

    public Project() {
    }

    public Project(String workplace, String startTime, String endTime, String name, String post, String description, List<String> activities, List<String> tools) {
        this.workplace = workplace;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.post = post;
        this.description = description;
        this.activities = activities;
        this.tools = tools;
    }

    public Project(Project project) {
        this.id = new ObjectId().toString();
        this.workplace = project.getWorkplace();
        this.startTime = project.getStartTime();
        this.endTime = project.getEndTime();
        this.name = project.getName();
        this.post = project.getPost();
        this.description = project.getDescription();
        this.activities = project.getActivities();
        this.tools = project.getTools();
    }


    public Project rebuildProject(Project project) {
        return new Project(
                project.getId(),
                project.getWorkplace(),
                project.getStartTime(),
                project.getEndTime(),
                project.getName(),
                project.getPost(),
                project.getDescription(),
                project.getActivities(),
                project.getTools());
    }


}
