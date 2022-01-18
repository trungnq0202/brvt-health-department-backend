package com.trungngo.brvthealthreport.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class HealthReport {

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private String status;

    @Column
    @NotNull
    private int temperature;

    @Column
    @NotNull
    private int spO2;

    @Column
    @NotNull
    private String description;

    @Column
    private int patientId;

    public HealthReport() {
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getSpO2() {
        return spO2;
    }

    public String getDescription() {
        return description;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setSpO2(int spO2) {
        this.spO2 = spO2;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
