package com.Eteo.EteoCV.models.ProjectList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class CustomerObj {

    private String name;
    private List<ProjectDetails> project = new ArrayList<>();

    public CustomerObj() {
    }

    public CustomerObj(String name, List<ProjectDetails> project) {
        this.name = name;
        this.project = project;
    }
}
