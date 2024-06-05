package com.petclinic.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "owners")
public class Owner {
    @Id @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Double accountStatement;

    /* OneToMany est lazy par défaut donc il ne va pas automatiquement cherché les données de la table jointe
    * On peut forcer en utilisant @Query avec "JOIN FETCH" au dessus de la méthode dans le repository
    * OneToOne est "EAGER" par défaut par contre (le contraire)
    * Bonne pratique, désactiver par défaut le open in view dans properties : spring.jpa.open-in-view=false
    * Et faire les appels à la base de données uniquement dans les repositories ou services
    * */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    List<Pet> pets = new ArrayList<>();

    private Owner() {}

    public Owner(Long id, String firstName, String lastName, Double accountStatement) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountStatement = accountStatement;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void add(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }

    public double getAccountStatement() {
        return accountStatement;
    }

    public void setAccountStatement(double ownerToDebitNewAmount) {
        this.accountStatement = ownerToDebitNewAmount;
    }
}
