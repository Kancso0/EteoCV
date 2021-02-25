package com.Eteo.EteoCV.models.UserPersonal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


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

}
