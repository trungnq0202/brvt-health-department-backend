package com.trungngo.brvtpatientservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Patient {
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
    private String name;

    @Column
    @NotNull
    private String address;

    @Column
    @NotNull
    private Date dateOfBirth;

    @Column
    @NotNull
    private String phoneNumber;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    private int doctorId;

    @Column
    @NotNull
    private String role;

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public Integer getIllnessLevel() {
        return illnessLevel;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIllnessLevel(Integer illnessLevel) {
        this.illnessLevel = illnessLevel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
