package com.Eteo.EteoCV.models.Knowledge;

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

@Document(collection = "knowledges")
public class Knowledge {

    @Id
    private String id;
    private List<String> programming_language = new ArrayList<String>();
    private List<Type> frameworks = new ArrayList<Type>();
    private List<Type> cloud = new ArrayList<Type>();
    private List<Type> others = new ArrayList<Type>();
    private List<String> foreign_language = new ArrayList<String>();


}
