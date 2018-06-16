package com.proektwp.patient_evidence_app.model.DTO;

import java.io.Serializable;

public class MedicineDTO  implements Serializable {

    String name;
    int quantity;
    String typeOfReception;

    public MedicineDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTypeOfReception() {
        return typeOfReception;
    }

    public void setTypeOfReception(String typeOfReception) {
        this.typeOfReception = typeOfReception;
    }
}
