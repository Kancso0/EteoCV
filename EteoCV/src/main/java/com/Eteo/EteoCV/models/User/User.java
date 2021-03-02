package com.Eteo.EteoCV.models.User;

import com.Eteo.EteoCV.models.Knowledge.Knowledge;
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


@Document(collection = "user")
public class User {

    @Id
    private String id;
    private Personal personal;
    private Knowledge knowledge;
    private List<Project> projects = new ArrayList<>();

    public User() {
    }

    public User(String id, Personal personal, Knowledge knowledge, List<Project> projects) {
        this.id = id;
        this.personal = personal;
        this.knowledge = knowledge;
        this.projects = projects;
    }
}
