package com.trungngo.brvtadminservice.model;

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
    private Integer temperature;

    @Column
    @NotNull
    private Integer spO2;

    @Column
    @NotNull
    private String description;

    public HealthReport() {
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getSpO2() {
        return spO2;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public void setSpO2(Integer spO2) {
        this.spO2 = spO2;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
