package com.petclinic.invoice;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

@JsonDeserialize(using = InvoiceDeserializer.class)
public class Invoice {
    private final LocalDate visitDate;
    private final String visitPurpose;

    private final String ownerName;
    private final String petType;
    private final String petName;
    private double amount;

    public Invoice(LocalDate visitDate, String visitPurpose, String ownerName, String petType, String petName, Double amount) {
        this.visitDate = visitDate;
        this.visitPurpose = visitPurpose;
        this.ownerName = ownerName;
        this.petType = petType;
        this.petName = petName;
        this.amount = amount;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPetType() {
        return petType;
    }

    public String getPetName() {
        return petName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
