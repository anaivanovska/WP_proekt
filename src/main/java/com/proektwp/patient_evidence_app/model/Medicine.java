package com.proektwp.patient_evidence_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "medicine")
public class Medicine {

    @Id
    public String name;

    @Column
    public int quantity;

    @Column
    public String typeOfReception;

    public Medicine(){}
}
