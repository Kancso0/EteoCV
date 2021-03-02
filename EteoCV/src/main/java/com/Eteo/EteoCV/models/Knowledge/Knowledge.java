package com.Eteo.EteoCV.models.Knowledge;

import com.Eteo.EteoCV.payloads.request.Dto.KnowledgeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter


@Document(collection = "knowledges")
public class Knowledge {

    private List<Type> programming_language = new ArrayList<Type>();
    private List<Type> frameworks = new ArrayList<Type>();
    private List<Type> cloud = new ArrayList<Type>();
    private List<Type> others = new ArrayList<Type>();
    private List<Type> foreign_language = new ArrayList<Type>();

    public Knowledge() {
    }

    public Knowledge(List<Type> programming_language, List<Type> frameworks, List<Type> cloud, List<Type> others, List<Type> foreign_language) {
        this.programming_language = programming_language;
        this.frameworks = frameworks;
        this.cloud = cloud;
        this.others = others;
        this.foreign_language = foreign_language;
    }

    public Knowledge(KnowledgeDto knowledgeDto) {
        this.programming_language = knowledgeDto.getProgramming_language();
        this.frameworks = knowledgeDto.getFrameworks();
        this.cloud = knowledgeDto.getCloud();
        this.others = knowledgeDto.getOthers();
        this.foreign_language = knowledgeDto.getForeign_language();
    }
}
