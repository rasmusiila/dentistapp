package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.exception.DentistVisitExistsException;
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

    public void addDentistVisit(DentistEntity dentist, LocalDate visitDate, LocalTime visitTime)
            throws DentistVisitExistsException {
        if (!dateTimeCombinationExists(visitDate, visitTime, dentist.getId())) {
            // The validation happens here programmatically because I was unable to find a way to enforce the overlapping rule to the entities
            dentistVisitRepository.save(new DentistVisitEntity(visitDate, visitTime, dentist));
        } else {
            throw new DentistVisitExistsException("Date and time combination already exists");
        }
    }

    public List<DentistVisitEntity> getDentistVisits() {
        return (List<DentistVisitEntity>) dentistVisitRepository.findAll();
    }

    public DentistVisitEntity getDentistVisitById(Long id) {
        return dentistVisitRepository.findOne(id);
    }

    public void deleteDentistVisit(Long id) {
        dentistVisitRepository.delete(id);
    }

    public void updateDentistVisit(Long id, DentistEntity dentist, LocalDate visitDate, LocalTime visitTime) {
        // TODO: maybe you should check if the entity exists in database before you update
        DentistVisitEntity dentistVisit = dentistVisitRepository.findOne(id);
        if (dentistVisit != null) {
            dentistVisit.setDentist(dentist);
            dentistVisit.setVisitDate(visitDate);
            dentistVisit.setVisitTime(visitTime);
            dentistVisitRepository.save(dentistVisit);
        }
    }

    public boolean dateTimeCombinationExists(LocalDate date, LocalTime time, Long dentistId) {
        for (DentistVisitEntity dentistVisit: dentistVisitRepository.findAll()) {
            if (dentistVisit.getDentist().getId().equals(dentistId) &&
                    dentistVisit.getVisitDate().compareTo(date) == 0 &&
                    dentistVisit.getVisitTime().compareTo(time) == 0) {
                return true;
            }
        }
        return false;
    }


    public long countDentistVisits() {
        return dentistVisitRepository.count();
    }
}
