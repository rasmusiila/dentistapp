package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DentistVisitRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DentistVisitRepository dentistVisitRepository;

    @Autowired
    private DentistRepository dentistRepository;

    private static DentistVisitEntity dentistVisitEntity;
    private static boolean setUpIsDone;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return; // the setup only needs to run once but BeforeClass cannot be used since it makes the method static
        }
        System.out.println("SETUP");
        entityManager.persist(new DentistEntity("Heli Adamson"));
        entityManager.flush();

        dentistVisitEntity = new DentistVisitEntity(LocalDate.now().plusDays(1),
                LocalTime.NOON, dentistRepository.findOne((long) 1));

        entityManager.persist(dentistVisitEntity);
        entityManager.flush();

        setUpIsDone = true;
    }

    @Test
    @Rollback(false)
    public void whenFindById_thenReturnDentistVisitEntity() {
        System.out.println(dentistVisitRepository.findAll());
        DentistVisitEntity found = dentistVisitRepository.findOne((long) 1);
        System.out.println(dentistVisitEntity);
        Assert.assertEquals(dentistVisitEntity.getVisitDate(), found.getVisitDate());
        Assert.assertEquals(dentistVisitEntity.getVisitTime(), found.getVisitTime());
    }

    @Test
    @Rollback(false)
    public void whenFindByWrongId_thenReturnNull() {
        DentistVisitEntity found = dentistVisitRepository.findOne((long) 2);
        Assert.assertNull(found);
    }

    @Test
    @Rollback(false)
    public void whenCount_thenReturnOne() {
        System.out.println(dentistVisitRepository.findAll());
        Assert.assertEquals(1, dentistVisitRepository.count());
    }
}
