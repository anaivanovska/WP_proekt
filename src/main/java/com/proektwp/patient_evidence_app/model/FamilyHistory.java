package com.proektwp.patient_evidence_app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "family_history")
public class FamilyHistory {

    @Id
    @GeneratedValue
    public Long id;
}
