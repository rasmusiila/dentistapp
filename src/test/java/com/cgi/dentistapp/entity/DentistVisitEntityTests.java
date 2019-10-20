package com.cgi.dentistapp.entity;

import org.hibernate.TransientPropertyValueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DentistVisitEntityTests {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void constructorTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.NOON, dentist);
        Assert.assertEquals(name, dentistVisitEntity.getDentist().getName());
        Assert.assertEquals(LocalDate.now(), dentistVisitEntity.getVisitDate());
        Assert.assertEquals(LocalTime.NOON, dentistVisitEntity.getVisitTime());
    }

    @Test
    public void getterAndSetterTest() {
        String name1 = "Heli Adamson";
        DentistEntity dentist1 = new DentistEntity(name1);
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.NOON, dentist1);
        String name2 = "Sander Tamm";
        DentistEntity dentist2 = new DentistEntity(name2);

        dentistVisitEntity.setDentist(dentist2);
        dentistVisitEntity.setVisitDate(LocalDate.now().plusMonths(1));
        dentistVisitEntity.setVisitTime(LocalTime.of(16, 0));
        Assert.assertEquals(name2, dentistVisitEntity.getDentist().getName());
        Assert.assertEquals(LocalDate.now().plusMonths(1), dentistVisitEntity.getVisitDate());
        Assert.assertEquals(LocalTime.of(16, 0), dentistVisitEntity.getVisitTime());
    }

    @Test(expected = Test.None.class)
    public void successfulInsertionTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now().plusDays(1), LocalTime.NOON, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = IllegalStateException.class)
    public void nonExistingDentistTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.NOON, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullDentistTest() {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.NOON, null);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullDateTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(null, LocalTime.NOON, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullTimeTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), null, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void pastDateTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now().minusDays(1), LocalTime.NOON, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void farFutureDateTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now().plusYears(2), LocalTime.NOON, dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void earlyMorningTimeTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.of(6,0), dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void lateNightTimeTest() {
        String name = "Heli Adamson";
        DentistEntity dentist = new DentistEntity(name);

        entityManager.persist(dentist);
        entityManager.flush();

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(LocalDate.now(), LocalTime.of(23,0), dentist);

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();
    }
}
