package com.trungngo.brvtloginservice.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by CoT on 10/14/17.
 */

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    @NotNull
    private String role;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
