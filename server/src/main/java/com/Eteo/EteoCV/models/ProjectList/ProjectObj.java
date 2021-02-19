package com.Eteo.EteoCV.models.ProjectList;

import com.Eteo.EteoCV.models.ProjectList.CustomerObj;
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

@Document(collection = "projectList")
public class ProjectObj {

    @Id
    private String id;
    private String workplace;
    private List<CustomerObj> costumer = new ArrayList<>();

}
