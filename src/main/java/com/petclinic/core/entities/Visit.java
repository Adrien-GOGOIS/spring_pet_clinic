package com.petclinic.core.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity @Table(name = "visits")
public class Visit {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "reference_number") // Pas obligatoire, _ ajouté automatiquement
    private String referenceNumber;

    private LocalDate date;
    private String purpose;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    private Visit() {}

    public Visit(Long id, String referenceNumber, LocalDate date, String purpose) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.date = date;
        this.purpose = purpose;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) && Objects.equals(referenceNumber, visit.referenceNumber) && Objects.equals(date, visit.date) && Objects.equals(purpose, visit.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceNumber, date, purpose);
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }
    public String getPurpose() {
        return purpose;
    }
    public LocalDate getDate() {
        return date;
    }
    public Long getId() {
        return id;
    }
}
