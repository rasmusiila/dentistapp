package com.cgi.dentistapp.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dentist")
public class DentistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Dentist name must not be null or blank")
    @Size(min = 1, max = 50, message = "Dentist name length must be between 1 to 50")
    private String name;

    public DentistEntity() {
    }

    public DentistEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Dentist[id=%d, name='%s']", id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
