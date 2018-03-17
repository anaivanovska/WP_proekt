package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    public String userId;

    public String firstName;
    public String lastName;
    public String role;
    public String password;


    public User(){}

    public User(String userId, String firstName, String lastName, String role, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }

    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.role = user.role;
        this.password = user.password;
    }
}
