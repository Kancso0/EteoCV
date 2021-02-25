package com.Eteo.EteoCV.models.User;

import com.Eteo.EteoCV.models.Project;
import com.Eteo.EteoCV.models.UserPersonal.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private Personal personal;
    private List<Project> projects = new ArrayList<>();
}
