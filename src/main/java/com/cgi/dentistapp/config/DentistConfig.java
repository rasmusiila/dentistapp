package com.cgi.dentistapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "dentists.properties", encoding = "UTF-8")
public class DentistConfig {
    @Value("#{'${dentist.names}'.split(', ')}")
    private List<String> dentistNames;

    public List<String> getDentistNames() {
        return dentistNames;
    }

    public void setDentistNames(List<String> dentistNames) {
        this.dentistNames = dentistNames;
    }
}
