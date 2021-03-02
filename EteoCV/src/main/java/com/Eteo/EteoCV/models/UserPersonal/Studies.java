package com.Eteo.EteoCV.models.UserPersonal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter


public class Studies {

    private String name;
    private String year;

    public Studies() {
    }

    public Studies(String name, String year) {
        this.name = name;
        this.year = year;
    }
}
