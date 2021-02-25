package com.Eteo.EteoCV.models.Knowledge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private String name;
    private List<String> type = new ArrayList<>();
}
