package com.Eteo.EteoCV.models.UserPersonal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sectors {

    private String name;
    private List<String> sector;
}
