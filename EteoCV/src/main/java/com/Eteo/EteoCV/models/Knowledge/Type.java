package com.Eteo.EteoCV.models.Knowledge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter


public class Type {

    private String name;
    private List<String> type = new ArrayList<>();

    public Type() {
    }

    public Type(String name, List<String> type) {
        this.name = name;
        this.type = type;
    }
}
