package com.trungngo.brvtadminservice.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class User {

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String address;

    @Column
    @NotNull
    private String dateOfBirth;

    @Column
    @NotNull //NOT WORKING, ALL ATTRIBUTES HERE CAN STILL BE NULL FOR SOME REASON
    private String phoneNumber;

    @Column(unique = true)
    @NotNull //NOT WORKING, ALL ATTRIBUTES HERE CAN STILL BE NULL FOR SOME REASON
    private String email;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
