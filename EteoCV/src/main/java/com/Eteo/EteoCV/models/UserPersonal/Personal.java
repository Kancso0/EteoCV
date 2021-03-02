package com.Eteo.EteoCV.models.UserPersonal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter

public class Personal {


    private String name;
    private String year_of_birth;
    private String role;
    private String position;
    private List<String> strengths;
    private String studium;
    private String studiumYear;
    private String sector;
    private List<String> certificates;

    public Personal() {
    }

    public Personal(String name, String year_of_birth, String role, String position, List<String> strengths, String studium, String studiumYear, String sector, List<String> certificates) {
        this.name = name;
        this.year_of_birth = year_of_birth;
        this.role = role;
        this.position = position;
        this.strengths = strengths;
        this.studium = studium;
        this.studiumYear = studiumYear;
        this.sector = sector;
        this.certificates = certificates;
    }
}
