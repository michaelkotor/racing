package com.example.racing.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Pilot implements Serializable {
    @Id
    private long id;
    private String name;
    private int age;
    private String nationality;
    private int number;
    @ElementCollection
    private List<String> titles;
    @ManyToOne
    private Report report;


}
