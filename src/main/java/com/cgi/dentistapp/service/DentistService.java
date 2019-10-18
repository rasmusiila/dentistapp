package com.cgi.dentistapp.service;

import com.cgi.dentistapp.config.DentistConfig;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DentistService {
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private DentistConfig dentistConfig;

    public List<DentistEntity> getDentists() {
        return (List<DentistEntity>) dentistRepository.findAll();
    }

    public DentistEntity getDentistById(Long id) {
        // TODO: maybe this should allow a null argument and then return null as a response
        return dentistRepository.findOne(id);
    }

    public void addDentist(String name) {
        // This method will not be used by the live application but it's used to add Dentists to database.
        dentistRepository.save(new DentistEntity(name));
    }

    public long countDentists() {
        return dentistRepository.count();
    }

    public void addDentistsIfNone() {
        // This method is used only to add a list of dentist names to the database when the application is started
        if (countDentists() == 0) {
            for (String dentistName: dentistConfig.getDentistNames()) {
                addDentist(dentistName);
            }
        }
    }
}
