package com.Eteo.EteoCV.models.PersonalProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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


}
