package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Indexed
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    public String userId;

    @NotNull
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO)
    public String firstName;

    @NotNull
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO)
    public String lastName;

    @NotNull
    public Role role;

    @JsonIgnore
    @NotNull
    public String password;

    @NotNull
    public String email;

    @NotNull
    public String phoneNumber;


    @NotNull
    public String address;

    public User(){}

    public User(String userId, String firstName, String lastName, Role role, String password, String email, String phoneNumber, String address) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.role = user.role;
        this.password = user.password;
        this.email = user.email;
        this.phoneNumber = user.phoneNumber;
        this.address = user.address;
    }


}
