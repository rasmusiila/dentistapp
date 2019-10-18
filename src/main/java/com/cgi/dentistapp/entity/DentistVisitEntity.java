package com.cgi.dentistapp.entity;

import com.cgi.dentistapp.validation.DentistVisitDateConstraint;
import com.cgi.dentistapp.validation.DentistVisitTimeConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Dentist visit date must not be null")
    @DentistVisitDateConstraint
    private LocalDate visitDate;
    @NotNull(message = "Dentist visit time must not be null")
    @DentistVisitTimeConstraint
    private LocalTime visitTime;

    @ManyToOne
    @NotNull(message = "Dentist must not be null")
    private DentistEntity dentist;

    public DentistVisitEntity() {
    }

    public DentistVisitEntity(LocalDate visitDate, LocalTime visitTime, DentistEntity dentist) {
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public DentistEntity getDentist() {
        return dentist;
    }

    public void setDentist(DentistEntity dentist) {
        this.dentist = dentist;
    }
}
