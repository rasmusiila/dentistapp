package com.cgi.dentistapp.dto;

import javax.validation.constraints.Size;

public class DentistDTO {
    @Size(min = 1, max = 50)
    private String name;

    public DentistDTO() {
    }

    public DentistDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
