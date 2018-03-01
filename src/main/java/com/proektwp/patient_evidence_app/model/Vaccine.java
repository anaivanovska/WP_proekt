package com.proektwp.patient_evidence_app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    public String name;

    @Column
    @Temporal(TemporalType.DATE)
    public Date dateOfReceipt;

    @Column
    public Boolean revaccination;

    @Column
    @Temporal(TemporalType.DATE)
    public Date dateOfRevaccination;


    @ManyToOne
    public Patient patient;

    public Vaccine(){};


}
