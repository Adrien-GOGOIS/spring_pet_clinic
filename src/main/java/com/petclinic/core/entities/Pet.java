package com.petclinic.core.entities;

import jakarta.persistence.*;

@Entity @Table(name = "pets")
public class Pet {
    @Id @GeneratedValue
    private Long id;

    private String type;
    private String name;

    public Pet() {
    }

    public Pet(Long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
