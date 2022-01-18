package com.trungngo.brvtadminservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Patient extends User{
    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private Integer illnessLevel;

    @Column
    @NotNull
    private String status;

    @Column
    @NotNull
    @OneToMany
    List<HealthReport> healthReportList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIllnessLevel() {
        return illnessLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIllnessLevel(Integer illnessLevel) {
        this.illnessLevel = illnessLevel;
    }

    public List<HealthReport> getHealthReportList() {
        return healthReportList;
    }

    public void setHealthReportList(List<HealthReport> healthReportList) {
        this.healthReportList = healthReportList;
    }

}
