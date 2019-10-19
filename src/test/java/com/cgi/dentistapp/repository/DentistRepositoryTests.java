package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DentistRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DentistRepository dentistRepository;

    private DentistEntity dentist = new DentistEntity("Heli Adamson");
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return; // the setup only needs to run once but BeforeClass cannot be used since it makes the method static
        }
        System.out.println("SETUP");
        entityManager.persist(dentist);
        entityManager.flush();
        setUpIsDone = true;
    }

    @Test
    @Rollback(false)
    public void whenFindById_thenReturnDentist() {
        System.out.println(dentistRepository.findAll());
        DentistEntity found = dentistRepository.findOne((long) 1);
        System.out.println(found);
        Assert.assertEquals(dentist.getName(), found.getName());
    }

    @Test
    @Rollback(false)
    public void whenFindByWrongId_thenReturnNull() {
        DentistEntity found = dentistRepository.findOne((long) 2);
        Assert.assertNull(found);
    }

    @Test
    @Rollback(false)
    public void whenCount_thenReturnOne() {
        System.out.println("uh");
        System.out.println(dentistRepository.findAll());
        Assert.assertEquals(1, dentistRepository.count());
    }
}
