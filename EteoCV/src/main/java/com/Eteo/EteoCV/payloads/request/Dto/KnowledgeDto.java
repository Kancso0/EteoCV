package com.Eteo.EteoCV.payloads.request.Dto;

import com.Eteo.EteoCV.models.Knowledge.Type;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter

public class KnowledgeDto {

    private List<Type> programming_language = new ArrayList<Type>();
    private List<Type> frameworks = new ArrayList<Type>();
    private List<Type> cloud = new ArrayList<Type>();
    private List<Type> others = new ArrayList<Type>();
    private List<Type> foreign_language = new ArrayList<Type>();

    public KnowledgeDto() {
    }

    public KnowledgeDto(List<Type> programming_language, List<Type> frameworks, List<Type> cloud, List<Type> others, List<Type> foreign_language) {
        this.programming_language = programming_language;
        this.frameworks = frameworks;
        this.cloud = cloud;
        this.others = others;
        this.foreign_language = foreign_language;
    }

}
