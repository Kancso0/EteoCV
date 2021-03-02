package com.Eteo.EteoCV.models.UserPersonal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter


public class Sectors {

    private String name;
    private List<String> sector;

    public Sectors() {
    }

    public Sectors(String name, List<String> sector) {
        this.name = name;
        this.sector = sector;
    }
}
