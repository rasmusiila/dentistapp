package com.cgi.dentistapp.dto;

import com.cgi.dentistapp.validation.DTODateTimeConstraint;
import com.cgi.dentistapp.validation.DTOOverlappingDateTimeConstraint;
import com.cgi.dentistapp.validation.DentistVisitDateConstraint;
import com.cgi.dentistapp.validation.DentistVisitTimeConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@DTODateTimeConstraint
@DTOOverlappingDateTimeConstraint
public class DentistVisitDTO {

//    @Size(min = 1, max = 50)
//    private String dentistName;

    private Long id;

    @NotNull
    @Min(1)
    private Long dentistId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @DentistVisitDateConstraint
    private LocalDate visitDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @DentistVisitTimeConstraint
    private LocalTime visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(Long dentistId, LocalDate visitDate, LocalTime visitTime) {
        this.dentistId = dentistId;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

//    public String getDentistName() {
//        return dentistName;
//    }
//
//    public void setDentistName(String dentistName) {
//        this.dentistName = dentistName;
//    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
