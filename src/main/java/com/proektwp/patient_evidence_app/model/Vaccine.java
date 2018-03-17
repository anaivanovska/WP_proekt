package com.proektwp.patient_evidence_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vaccine")
public class Vaccine {

    @EmbeddedId
    public VaccineID vaccineID;

    @Temporal(TemporalType.DATE)
    public Date dateOfReceipt;



    @MapsId("userId")
    @ManyToOne
    @JsonIgnore
    public Patient patient;

    public Vaccine(){};

    public Vaccine(VaccineID vaccineID, Date dateOfReceipt, Patient patient) {
        this.vaccineID = vaccineID;
        this.dateOfReceipt = dateOfReceipt;
        this.patient = patient;
    }
}
