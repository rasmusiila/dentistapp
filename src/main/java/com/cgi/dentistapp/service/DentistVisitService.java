package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {
    @Autowired
    private DentistVisitRepository dentistVisitRepository;

    public void addDentistVisit(DentistEntity dentist, LocalDate visitDate, LocalTime visitTime) {
        dentistVisitRepository.save(new DentistVisitEntity(visitDate, visitTime, dentist));
    }

    public List<DentistVisitEntity> getDentistVisits() {
        return (List<DentistVisitEntity>) dentistVisitRepository.findAll();
    }
}
