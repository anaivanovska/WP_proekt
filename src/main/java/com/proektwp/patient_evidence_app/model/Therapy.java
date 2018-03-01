package com.proektwp.patient_evidence_app.model;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "therapy")
public class Therapy {

    @Id
    public Date date;

    @Column
    public String typeOfTherapy;


    @OneToMany(cascade = CascadeType.REMOVE)
    public List<Medicine> medicines;

    @OneToOne(mappedBy = "therapy")
    public HealthExamination healthExamination;



    public Therapy(){}

}
