package com.Eteo.EteoCV.models;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "project")
public class Project {

    @Id
    private String id;
    private String workplace;
    private String time;
    private String name;
    private String post;
    private String description;
    private List<String> activities = new ArrayList<>();
    private List<String> tools = new ArrayList<>();

}
