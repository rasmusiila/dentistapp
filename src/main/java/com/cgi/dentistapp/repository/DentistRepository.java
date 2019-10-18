package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistEntity;
import org.springframework.data.repository.CrudRepository;

public interface DentistRepository extends CrudRepository<DentistEntity, Long> {
}
