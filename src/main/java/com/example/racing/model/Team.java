package com.example.racing.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Team implements Serializable {

    @Id
    private long id;
    private String name;
    private String color;
    @ElementCollection
    private List<String> bikes;

    @OneToOne
    private Pilot beginner;
    @OneToOne
    private Pilot professional;


}
