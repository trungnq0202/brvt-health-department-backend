package com.trungngo.brvtadminservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Doctor extends User{

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private Integer careerLevel;

    @Column
    @NotNull
    @OneToMany
    List<Patient> patientList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(Integer careerLevel) {
        this.careerLevel = careerLevel;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}

