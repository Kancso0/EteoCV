package com.Eteo.EteoCV.models.properties;

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

@Document(collection = "properties")
public class Properties {

    private List<String>  tools = new ArrayList<>();
    private List<String>  activities = new ArrayList<>();

}
