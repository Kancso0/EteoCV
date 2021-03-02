package com.Eteo.EteoCV.models.PersonalProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter

@Document(collection = "personalProperties")
public class PersonalProperties {

    private List<String> role = new ArrayList<>();
    private List<String> position = new ArrayList<>();
    private List<String> strengths = new ArrayList<>();
    private List<String> studies = new ArrayList<>();
    private List<String> sectors = new ArrayList<>();
    private List<String> certificates = new ArrayList<>();
    private List<String>  tools = new ArrayList<>();
    private List<String>  activities = new ArrayList<>();

    public PersonalProperties() {
    }

    public PersonalProperties(List<String> role, List<String> position, List<String> strengths, List<String> studies, List<String> sectors, List<String> certificates, List<String> tools, List<String> activities) {
        this.role = role;
        this.position = position;
        this.strengths = strengths;
        this.studies = studies;
        this.sectors = sectors;
        this.certificates = certificates;
        this.tools = tools;
        this.activities = activities;
    }
}
