package com.Eteo.EteoCV.models.ProjectList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter


public class ProjectDetails {

    private String name;
    private String description;

    public ProjectDetails() {
    }

    public ProjectDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
